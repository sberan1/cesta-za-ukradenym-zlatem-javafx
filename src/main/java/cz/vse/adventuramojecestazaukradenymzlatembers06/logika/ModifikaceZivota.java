package cz.vse.adventuramojecestazaukradenymzlatembers06.logika;

public class ModifikaceZivota implements INavrat{

    private HerniPlan plan;
    private int modifikator;

    public ModifikaceZivota(HerniPlan plan, int modifikator) {
        this.plan = plan;
        this.modifikator = modifikator;
    }

    /**
     *
     */
    @Override
    public void navratovaHodnota() {
        plan.uberZivoty(modifikator);
    }
}
