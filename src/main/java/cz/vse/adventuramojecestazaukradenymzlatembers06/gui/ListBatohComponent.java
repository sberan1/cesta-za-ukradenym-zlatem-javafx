package cz.vse.adventuramojecestazaukradenymzlatembers06.gui;


import cz.vse.adventuramojecestazaukradenymzlatembers06.main.Adventura;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import cz.vse.adventuramojecestazaukradenymzlatembers06.logika.Hra;
import cz.vse.adventuramojecestazaukradenymzlatembers06.observer.Observer;


public class ListBatohComponent extends FlowPane implements Observer {

    private Hra hra = Hra.getSingleton();

    public ListBatohComponent() {
        hra.getHerniPlan().getBatuzek().register(this);
        Label label = new Label("Obsah batohu:");
        getChildren().add(label);

        update();
    }




    @Override
    public void update() {
        hra = Hra.getSingleton();
        getChildren().clear();
        hra.getHerniPlan().getBatuzek().getObsah().forEach(vec -> {
            ImageView imageView = new ImageView(vec.getObrazek());
            imageView.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2) {
                    if (vec != null) {
                        String odpoved = hra.zpracujPrikaz("pouzij " + vec.getNazev());
                        Adventura.getTextArea().appendText("\n" + odpoved + "\n");
                        System.out.println("clicked on " + vec.getNazev() + "!");
                    }
                }
            });
            getChildren().add(imageView);
        });
    }
}

