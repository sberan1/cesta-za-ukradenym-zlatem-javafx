package cz.vse.adventuramojecestazaukradenymzlatembers06.logika;

import java.text.Normalizer;
/**
 * Trida PrikazVymen - trida pro realizaci prikazu vymen, kter nam dovoluje uskutecnit vymenu, pokud se tam nejaka nachazi
 *
 * @author sBeran1
 */
public class PrikazVymen implements IPrikaz{
    private int counter = 0; //pocita pouziti prikazu
    private static final String NAZEV = "vymen"; //nazev prikazu a jeho zneni pro pouziti
    private HerniPlan plan;//instance herniho planu
    boolean viditelnost = true;


    /**
     * Konstruktor tridy
     *
     * @param plan herni plan obsahujici batoh a mistnosti
     */
    public PrikazVymen(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Co se stane pri exekuci prikazu
     *
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return textovy retezec obsahujici zpravu o tom co se stalo a dlouhy popis mistnosti
     */
    @Override
    public String provedPrikaz(String... parametry) {
        counter++;
        int pocitadlo = 0; //pomocna promenna pro zjisteni, zda ma uzivatel vsechny potrebne veci u sebe
        Vymena vymena = plan.getAktualniProstor().getVymena(); //ulehceni pristupu k vymene v aktualnim prostoru
        if (vymena != null) {
            if (parametry.length == 0) {
                return "musis napsat co chces vymenit";
            }
            if (parametry.length > 1) {
                return "Co z toho chces vymenit?";
            }
            if (Normalizer.normalize(parametry[0], Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").equalsIgnoreCase(Normalizer.normalize(vymena.getKratkyNazev(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""))) {
                //zkoumame, jestli ma uzivatel vsechny potrebne veci a podle toho inicializujeme prikaz
                for (var item : vymena.getOcekavaneVeci()){
                   if(plan.getBatuzek().getObsah().contains(item)){
                       pocitadlo++;
                   }
                }
                if (pocitadlo == vymena.getOcekavaneVeci().size()){
                    for (var item : vymena.getOcekavaneVeci()){
                        plan.getBatuzek().odeberVec(item.getNazev());
                    }
                    for (var item : vymena.getNavratoveHodnoty()){
                        item.navratovaHodnota();
                    }
                    if (vymena.getOdemceniMistnosti()[0] != null && vymena.getOdemceniMistnosti()[1] != null){
                        vymena.getOdemceniMistnosti()[0].setVychod(vymena.getOdemceniMistnosti()[1]);
                    }
                    return vymena.getTextKZobrazeni() + "\n" + plan.getAktualniProstor().dlouhyPopis();
                }
                if (vymena.getTrestZaNesplneni() != null){
                    Object item = vymena.getTrestZaNesplneni();
                    if (item.getClass().getName().equals("java.lang.Integer")) {
                        plan.uberZivoty((Integer) item);
                    }
                    if (item.getClass().getName().equals("logika.Prostor")){
                        plan.setAktualniProstor((Prostor) item);
                    }
                    return "nesplnil jsi vymenu, potkal te trest" + plan.getAktualniProstor().dlouhyPopis();
                }
                return "nemas potrebne veci pro tuto vymenu, bez hledat";
            }
            return "tohle tu neni, zkus zadat prikaz vymeny a podivat se co muzes vymenit";
        }
        return "hele tady neni moc co vymenovat";
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

