package cz.vse.adventuramojecestazaukradenymzlatembers06.logika;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PridaniVeciDoInvenatare implements INavrat{
    private List<Vec> veci;
    private Batoh batuzek;

    /**
     * Přidá věci do inventáře
     * @param batuzek batoh
     * @param vec věci co se mají přidat
     */
    public PridaniVeciDoInvenatare(Batoh batuzek, Vec... vec) {
        veci = new ArrayList<>();
        this.batuzek = batuzek;
        veci.addAll(Arrays.asList(vec));
    }

    /**
     * Přidá věci do inventáře
     */
    @Override
    public void navratovaHodnota() {
        for (var item : veci) {
            item.pridatVideniVeci();
            batuzek.vlozVec(item);
        }
    }
}
