package cz.vse.adventuramojecestazaukradenymzlatembers06.logika;

public class PrikazIdkfa implements IPrikaz{

    private int counter = 0; //pocita pouziti prikazu
    private static final String NAZEV = "idkfa"; //nazev prikazu a jeho zneni ve hre
    private HerniPlan plan; //instance trida hra
    boolean viditelnost = false;


    public PrikazIdkfa(HerniPlan plan) {
        this.plan = plan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        counter++;
        if (parametry.length == 0) {
            plan.getBatuzek().setVelikostBatuzku(plan.getVeci().size());
            for (var item : plan.getVeci()) {
                plan.getBatuzek().vlozVec(item);
            }
            return "Sebral jsi vsechny veci";
        }
        return "nechapu";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }

    @Override
    public int getCounter() {
        return counter;
    }

    @Override
    public boolean isViditelny() {
        return viditelnost;
    }
}
