package cz.vse.adventuramojecestazaukradenymzlatembers06.logika;

public class ModifikaceZivota implements INavrat{

    private HerniPlan plan;
    private int modifikator;

    public ModifikaceZivota(HerniPlan plan, int modifikator) {
        this.plan = plan;
        this.modifikator = modifikator;
    }

    /**
     * Ubere hracovy zivoty z herniho planu
     */
    @Override
    public void navratovaHodnota() {
        plan.uberZivoty(modifikator);
    }
}
