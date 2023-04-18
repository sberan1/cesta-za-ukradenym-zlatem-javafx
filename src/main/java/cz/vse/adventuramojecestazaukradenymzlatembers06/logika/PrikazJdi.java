package cz.vse.adventuramojecestazaukradenymzlatembers06.logika;

import java.text.Normalizer;

/**
 *  Třída PrikazJdi implementuje pro hru příkaz jdi.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 * @author     Jarmila Pavlickova, Luboš Pavlíček
 * @version    pro školní rok 2016/2017
 */
public class PrikazJdi implements IPrikaz {
    private int counter = 0; //pocita pouziti prikazu
    private static final String NAZEV = "jdi"; //nazev prikazu a jeho zneni pro pouziti
    private Hra hra; //instance tridy hra
    private HerniPlan plan; //instance herniho planu
    boolean viditelnost = true;


    /**
    *  Konstruktor třídy
    *  
    *  @param hra objekt hry, ze které je možné získat herní plán
    */    
    public PrikazJdi(Hra hra) {
        this.hra = hra;
        this.plan = hra.getHerniPlan();
    }

    /**
     *  Provádí příkaz "jdi". Zkouší se vyjít do zadaného prostoru. Pokud prostor
     *  existuje, vstoupí se do nového prostoru. Pokud zadaný sousední prostor
     *  (východ) není, vypíše se chybové hlášení.
     *
     * @param parametry - jako  parametr obsahuje jméno prostoru (východu),
     *                         do kterého se má jít.
     * @return zpráva, kterou vypíše hra hráči
     */ 
    @Override
    public String provedPrikaz(String... parametry) {
        counter++;
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Kam mám jít? Musíš zadat jméno východu";
        }
        if (parametry.length > 1) {
            return "No a prosimvas to si mam jako vybrat do jake mistnosti mam jit?";
        }


        String smer = Normalizer
                .normalize(parametry[0], Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "").toLowerCase();


        // zkoušíme přejít do sousedního prostoru
        Prostor sousedniProstor = plan.getAktualniProstor().vratSousedniProstor(smer);

        if (sousedniProstor == null) {
            return "Tam se odsud jít nedá!";
        }
        else if (sousedniProstor.isZamceno()){
            return "Tam je zamčeno, tam se nedostaneš\n";
        } else if (!sousedniProstor.isViditelny() && sousedniProstor.getZivotnost()>0) {
            return "Tam se odsud jít nedá!";
        } else {
            plan.setAktualniProstor(sousedniProstor);
            sousedniProstor.pridatPruchod();
            sousedniProstor.videtVeciVMistnosti();
            if (sousedniProstor.equals(plan.getVyherniProstor())){
                hra.setKonecHry(true);
                return "Vyhrál jsi\n"+ hra.vratEpilog();
            }
            if (sousedniProstor.equals(plan.getProherniProstor())){
                hra.setEpilog("Skocils na starej, jednoduchej trik a prohrals troubo");
                hra.setKonecHry(true);
                return "zkus to radsi znovu, jo?\n" + hra.vratEpilog();
            }
            plan.uberZivoty(sousedniProstor.getModifikatorZivotu());
            for (var item : sousedniProstor.getVychody()){
                if (item.getZivotnost() != 999){
                    item.nastavPast(item.getZivotnost()-1);
                }
            }
            return sousedniProstor.getPopis() + "\n" + sousedniProstor.dlouhyPopis();
        }

    }
    
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

    /**
     * Vraci ciselnou hodnotu s poctem pouziti prikazu, pouzivano pro statistiky a nove vypisy
     *
     * @return pocet pouziti prikazu
     */
    @Override
    public int getCounter() {
        return counter;
    }

    @Override
    public boolean isViditelny() {
        return viditelnost;
    }
}
