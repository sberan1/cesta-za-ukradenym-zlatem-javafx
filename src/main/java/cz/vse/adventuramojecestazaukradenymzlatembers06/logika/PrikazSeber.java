package cz.vse.adventuramojecestazaukradenymzlatembers06.logika;

import java.text.Normalizer;
/**
 * Trida PrikazSeber - trida pro realizaci prikazu seber, kterym sbirame veci ze zeme
 *
 * @author sBeran1
 */
public class PrikazSeber implements IPrikaz {
    private int counter = 0; //pocita pouziti prikazu
    private static final String NAZEV = "seber"; //nazev prikazu a jeho zneni pro pouziti
    private HerniPlan plan; //instance herniho planu

    /**
     * Konstruktor tridy
     *
     * @param plan herni plan obsahujici batoh a mistnosti
     */
    public PrikazSeber(HerniPlan plan){
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
        if (parametry.length == 0){
            return "Coze mam sebrat? Vyber si!";
        }
        if (parametry.length > 1){
            return "Co z toho chces sebrat? Blazne!";
        }

        String nazevVeci = Normalizer
                .normalize(parametry[0], Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "").toLowerCase();

        Prostor aktualniProstor = plan.getAktualniProstor();


        if (aktualniProstor.obsahujeVec(nazevVeci)){
            Vec pozadovanaVec = aktualniProstor.vyberVeci(nazevVeci);
            if (pozadovanaVec == null){
                return parametry[0] + " je moc tezka, tu neuneses";
            }
            if (!pozadovanaVec.isViditelna()) {
                return parametry[0] + " tu neni ty blazne";
            }
            else {
                if (plan.getBatuzek().vlozVec(pozadovanaVec))
                {
                return "Sebral jsi " + pozadovanaVec.getNazev() +"\n"+ plan.getAktualniProstor().dlouhyPopis();
                }
                plan.getAktualniProstor().vlozVec(pozadovanaVec);
                return "Tam už se nic nevejde hele";
            }
        }
        return parametry[0] + "tu neni ty blazne";
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
}
