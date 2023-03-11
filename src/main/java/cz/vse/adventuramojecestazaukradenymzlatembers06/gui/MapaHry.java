package cz.vse.adventuramojecestazaukradenymzlatembers06.gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class MapaHry {
    private AnchorPane anchorPane = new AnchorPane();

    public MapaHry(){
        init();
    }

    private void init() {
        Image image = new Image(MapaHry.class.getResourceAsStream("Mapa.jpeg"), 1000.0, 750.0, false, false);
        ImageView imageView = new ImageView(image);
        anchorPane.getChildren().addAll(imageView);

    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }
}
