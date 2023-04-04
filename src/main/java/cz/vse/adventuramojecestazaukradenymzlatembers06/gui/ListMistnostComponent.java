package cz.vse.adventuramojecestazaukradenymzlatembers06.gui;

import cz.vse.adventuramojecestazaukradenymzlatembers06.logika.Hra;
import cz.vse.adventuramojecestazaukradenymzlatembers06.logika.Vec;
import cz.vse.adventuramojecestazaukradenymzlatembers06.main.Adventura;
import cz.vse.adventuramojecestazaukradenymzlatembers06.observer.Observer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

public class ListMistnostComponent extends FlowPane implements Observer {

    private Hra hra = Hra.getSingleton();

    public ListMistnostComponent() {
        hra.getHerniPlan().register(this);
        hra.getHerniPlan().getBatuzek().register(this);
        hra.getHerniPlan().getAktualniProstor().register(this);


        update();
    }




    @Override
    public void update() {
        getChildren().clear();
        for (Vec vec : hra.getHerniPlan().getAktualniProstor().getVeciVMistnosti()) {
            if (vec.isViditelna()){
                ImageView imageView = new ImageView(vec.getObrazek());
                imageView.setOnMouseClicked(mouseEvent -> {
                    if (mouseEvent.getClickCount() == 2) {
                        if (vec != null) {
                            String odpoved = hra.zpracujPrikaz("seber " + vec.getNazev());
                            Adventura.getTextArea().appendText("\n" + odpoved + "\n");
                            System.out.println("clicked on " + vec.getNazev() + "!");
                        }
                    }
                });
                getChildren().add(imageView);
            }
        }
    }
}

