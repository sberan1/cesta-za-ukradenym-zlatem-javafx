package cz.vse.adventuramojecestazaukradenymzlatembers06.main;

import cz.vse.adventuramojecestazaukradenymzlatembers06.gui.MapaHry;

import cz.vse.adventuramojecestazaukradenymzlatembers06.logika.Hra;
import cz.vse.adventuramojecestazaukradenymzlatembers06.logika.IHra;
import cz.vse.adventuramojecestazaukradenymzlatembers06.uiText.TextoveRozhrani;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Adventura extends Application {

    IHra hra = Hra.getSingleton();

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-text")) {
            TextoveRozhrani.main(args);
        } else {
            Adventura.launch(args);
        }
    }

    public void start(Stage stage) {
        BorderPane borderPane = new BorderPane();
        TextArea textArea = new TextArea();
        borderPane.setCenter(textArea);

        Label label = new Label("Zadej příkaz: ");
        label.setAlignment(Pos.CENTER);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        TextField uzivatelskyVstup = new TextField();
        HBox hBox = new HBox();
        hBox.getChildren().addAll(label, uzivatelskyVstup);
        borderPane.setBottom(hBox);

        AnchorPane anchorPane = pripravMapuHry();
        borderPane.setTop(anchorPane);

        nastavTextAreaAUzivatelskyVstup(textArea, uzivatelskyVstup);

        Scene scene = new Scene(borderPane, 400, 500);
        stage.setScene(scene);
        stage.show();
    }

    private AnchorPane pripravMapuHry() {
        MapaHry mapaHry = new MapaHry();
        AnchorPane anchorPane = mapaHry.getAnchorPane();
        return anchorPane;
    }

    private void nastavTextAreaAUzivatelskyVstup(TextArea textArea, TextField uzivatelskyVstup) {
        textArea.setEditable(false);
        textArea.setText(hra.vratUvitani());

        uzivatelskyVstup.setOnAction(actionEvent -> {
            String prikaz = uzivatelskyVstup.getText();
            String odpoved = hra.zpracujPrikaz(prikaz);
            textArea.appendText("\n" + odpoved + "\n");
            uzivatelskyVstup.setText("");
        });
    }


}
