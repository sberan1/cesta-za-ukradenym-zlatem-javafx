package cz.vse.adventuramojecestazaukradenymzlatembers06.logika;

/**
 * Trida PrikazVymeny - trida pro realizaci prikazu vymeny, ktery vypise informace o dostupne vymene
 *
 * @author sBeran1
 */
public class PrikazVymeny implements IPrikaz{
    private int counter = 0; //pocita pouziti prikazu
    private static final String NAZEV = "vymena"; //nazev prikazu a jeho zneni pro pouziti
    private HerniPlan plan; //instance tridy Prostor
    boolean viditelnost = true;

    /**
     * Konstruktor tridy
     *
     * @param plan Herni plan hry
     */
    public PrikazVymeny(HerniPlan plan) {
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
        if (plan.getAktualniProstor().getVymena() != null){
            return plan.getAktualniProstor().getVymena().toString() + "\n" + plan.getAktualniProstor().dlouhyPopis();
        }
        return "tady nic nevymenis kamo";
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
