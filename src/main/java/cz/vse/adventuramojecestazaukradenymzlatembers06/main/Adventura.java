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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Adventura extends Application {
    IHra hra = Hra.getSingleton();

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-text")){
            TextoveRozhrani.main(args);
        }
        else {
            Adventura.launch(args);
        }
    }
    @Override
    public void start(Stage primaryStage) {
        BorderPane parent = new BorderPane();
        TextArea textArea = new TextArea();
        TextField uzivatelskyVstup = new TextField();

        textArea.setMaxWidth(250);
        textArea.setMinHeight(700);
        textArea.setWrapText(true);

        Label label = new Label("Zadej prikaz: ");
        label.setAlignment(Pos.CENTER);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        HBox hBox = new HBox();
        hBox.getChildren().addAll(label, uzivatelskyVstup);
        VBox vbox = new VBox();
        vbox.getChildren().addAll(textArea, hBox);

        parent.setRight(vbox);

        AnchorPane ap = pripravMapuHry();
        parent.setLeft(ap);

        nastavTextAreaAUzivatelskyVstup(textArea, uzivatelskyVstup);
        //Button button = new Button("Press me!");
        //parent.setCenter(button);


        Scene value = new Scene(parent, 1250, 750);
        primaryStage.setScene(value);
        primaryStage.show();
    }

    private AnchorPane pripravMapuHry() {
        MapaHry mapaHry = new MapaHry();
        AnchorPane ap = mapaHry.getAnchorPane();
        return ap;
    }

    private void nastavTextAreaAUzivatelskyVstup(TextArea textArea, TextField vstup) {
        textArea.setEditable(false);
        textArea.setText(hra.vratUvitani());

        vstup.setOnAction(actionEvent -> {
            String prikaz = vstup.getText();
            String output = hra.zpracujPrikaz(prikaz);
            textArea.appendText("\n" + output + "\n" + "\n");
            vstup.setText("");
        });
    }
}
