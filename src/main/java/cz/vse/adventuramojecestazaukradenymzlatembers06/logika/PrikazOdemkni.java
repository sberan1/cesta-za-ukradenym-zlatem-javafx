package cz.vse.adventuramojecestazaukradenymzlatembers06.logika;

import java.text.Normalizer;

/**
 * Trida PrikazOdemkni - trida pro realizaci prikazu odemkni, ktery odemyka mistnost
 *
 * @author sBeran1
 */
public class PrikazOdemkni implements IPrikaz{
    private int counter = 0; //pocita pouziti prikazu
    private static final String NAZEV = "odemkni"; //nazev prikazu a jeho zneni pro pouziti
    private HerniPlan plan; //instance tridy herni plan
    boolean viditelnost = true;


    /**
     * Kontruktor tridy
     *
     * @param plan herni plan nesouci instance vsech mistnosti
     */
    public PrikazOdemkni(HerniPlan plan) {
        this.plan = plan;
    }

    /**
     * Co se provede po exekuci prikazu
     *
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return zprava kterou vypise hra hraci
     */
    @Override
    public String provedPrikaz(String... parametry) {
        counter++;
        if (parametry.length == 0) {
            //pokud chybi nazev mistnosti kterou chce nekdo odemknout
            return "Musite mit jasno v tom co chcete odemknout, co sem vubec lezete takhle nepripravenej sakra";
        }
        if (parametry.length > 1) {
            //pokud uzivatel zada vice mistnosti
            return "jezisi kriste a co z toho mam asi odemknout, vy jste jak z jara";
        }

        String nazevMistnosti = Normalizer.normalize(parametry[0], Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase();
        for (var mistnost : plan.getAktualniProstor().zamceneProstoryList()){
            if(nazevMistnosti.equals(mistnost.getnNormalnizedNazev()) && plan.getBatuzek().odeberVec("klic") && mistnost.isViditelny()) {
                    mistnost.odemknoutMistnost();
                    return "Odemkl jsi " + mistnost.getNazev() + "\n" + plan.getAktualniProstor().dlouhyPopis();
            }
        }
        return "Hele, tak bud nemas klic, nebo tam odsud cesta nevede, takze se pakuj";
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
