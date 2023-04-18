package cz.vse.adventuramojecestazaukradenymzlatembers06.logika;

import java.text.Normalizer;

/**
 * Class Batoh - trida pro realizaci batohu na uchovavani predmetu
 *
 * @author sBeran1
 */
public class PrikazPouzij implements IPrikaz{
    private int counter = 0; //pocita pouziti prikazu
    private static final String NAZEV = "pouzij"; //nazev prikazu a jeho zneni pro pouziti
    private HerniPlan plan; //instance herniho planu obsahujici
    boolean viditelnost = true;


    /**
     * Konstruktor tridy
     *
     * @param plan herni plan obsahujici batoh a mistnosti
     */
    public PrikazPouzij(HerniPlan plan) {
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
        if (parametry.length == 0) {
            return "musis napsat co mam pouzit";
        }
        if (parametry.length > 1) {
            return "Co z toho chces pouzit?";
        }
        for (var item : plan.getBatuzek().getObsah()) {
            if (Normalizer.normalize(parametry[0], Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").equalsIgnoreCase(item.getNormalizedNazev()) && item.isPouzitelna()){
                plan.getBatuzek().odeberVec(item.getNazev());
                if (item.getTyp() == Pouzitelnosti.LEKTVAR && plan.getPocetZivotu() <= 100) {
                    plan.uberZivoty(-item.getModifikator());
                }
                if (item.getTyp() == Pouzitelnosti.LEKTVAR && plan.getPocetZivotu() > 100){
                        plan.uberZivoty(plan.getPocetZivotu()-100);
                        return "Použil jsi " + item.getNazev() + "\n" + plan.getAktualniProstor().dlouhyPopis();
                    }
                }
                }

        return "bud to nemas v batohu nebo to nemuzes pouzit";
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
