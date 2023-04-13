package cz.vse.adventuramojecestazaukradenymzlatembers06.logika;

public class PrenosDoJineMistnosti implements INavrat{
    private HerniPlan plan;
    private Prostor mistnost;

    /**
     * Přesune hráče do jiné místnosti
     * @param plan herní plán
     * @param mistnost místnost do které se hráč přesune
     */
    public PrenosDoJineMistnosti(HerniPlan plan, Prostor mistnost) {
        this.plan = plan;
        this.mistnost = mistnost;
    }

    /**
     * Přesune hráče do jiné místnosti
     */
    @Override
    public void navratovaHodnota() {
        for (var prostor : mistnost.getVychody()){
            if (prostor.getZivotnost() != 999){
                prostor.nastavPast(prostor.getZivotnost()-1);

            }
        }
        plan.setAktualniProstor(mistnost);
    }
}
