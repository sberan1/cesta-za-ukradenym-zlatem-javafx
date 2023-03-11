package cz.vse.adventuramojecestazaukradenymzlatembers06.gui;

import cz.vse.adventuramojecestazaukradenymzlatembers06.logika.HerniPlan;
import cz.vse.adventuramojecestazaukradenymzlatembers06.logika.Hra;
import cz.vse.adventuramojecestazaukradenymzlatembers06.logika.IHra;
import cz.vse.adventuramojecestazaukradenymzlatembers06.observer.Observer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MapaHry implements Observer {
    private IHra hra = Hra.getSingleton();
    private ImageView postava = new ImageView(new Image(MapaHry.class.getResourceAsStream("postava.gif"), 50.0, 50.0, false, false));
    private int counter = 0;
    private AnchorPane anchorPane = new AnchorPane();

    public MapaHry(){
        init();
        aktualizuj();
        HerniPlan plan = hra.getHerniPlan();
        plan.register(this);
    }

    private void aktualizuj() {
        HerniPlan plan = hra.getHerniPlan();
        double posX = plan.getAktualniProstor().getPosLeft();
        double posY = plan.getAktualniProstor().getPosTop();
        AnchorPane.setLeftAnchor(postava, posX);
        AnchorPane.setTopAnchor(postava, posY);
    }

    private void init() {
        Image image = new Image(MapaHry.class.getResourceAsStream("Mapa.jpeg"), 1000.0, 750.0, false, false);
        ImageView imageView = new ImageView(image);
        anchorPane.getChildren().addAll(imageView,postava);

    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

    @Override
    public void update() {
        aktualizuj();
    }
}
