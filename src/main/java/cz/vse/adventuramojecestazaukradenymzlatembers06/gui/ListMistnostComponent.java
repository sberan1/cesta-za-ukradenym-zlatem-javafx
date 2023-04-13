package cz.vse.adventuramojecestazaukradenymzlatembers06.gui;

import cz.vse.adventuramojecestazaukradenymzlatembers06.logika.Hra;
import cz.vse.adventuramojecestazaukradenymzlatembers06.logika.Vec;
import cz.vse.adventuramojecestazaukradenymzlatembers06.main.Adventura;
import cz.vse.adventuramojecestazaukradenymzlatembers06.observer.Observer;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

/**
 * Třída ListMistnostComponent, která je komponentou pro zobrazení seznamu věcí v místnosti
 *
 * @author sberan1
 */
public class ListMistnostComponent extends FlowPane implements Observer {

    private Hra hra = Hra.getSingleton();

    /**
     * Konstruktor třídy
     */
    public ListMistnostComponent() {
        update();
    }


    /**
     * Metoda pro aktualizaci seznamu věcí v místnosti a přidání do panelu
     */

    @Override
    public void update() {
        hra = Hra.getSingleton();
        hra.getHerniPlan().register(this);
        hra.getHerniPlan().getBatuzek().register(this);
        hra.getHerniPlan().getAktualniProstor().register(this);
        getChildren().clear();
        for (Vec vec : hra.getHerniPlan().getAktualniProstor().getVeciVMistnosti()) {
            if (vec.isViditelna()){
                ImageView imageView = new ImageView(vec.getObrazek());
                imageView.setOnMouseClicked(mouseEvent -> {
                    prenastaveniMouseEventu(vec, mouseEvent);
                });
                getChildren().add(imageView);
            }
        }
    }

    private void prenastaveniMouseEventu(Vec vec, MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            if (vec != null) {
                String odpoved = hra.zpracujPrikaz("seber " + vec.getNazev());
                Adventura.getTextArea().appendText("\n" + odpoved + "\n");
                System.out.println("clicked on " + vec.getNazev() + "!");
            }
        }
    }
}

