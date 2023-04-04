package cz.vse.adventuramojecestazaukradenymzlatembers06.main;

import cz.vse.adventuramojecestazaukradenymzlatembers06.gui.ListBatohComponent;
import cz.vse.adventuramojecestazaukradenymzlatembers06.gui.ListMistnostComponent;
import cz.vse.adventuramojecestazaukradenymzlatembers06.gui.ListVychoduComponent;
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
    private static TextArea textArea = new TextArea();

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-text")) {
            TextoveRozhrani.main(args);
        } else {
            Adventura.launch(args);
        }
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane parent = new BorderPane();
        HBox hBox = new HBox();
        VBox vBox = new VBox();
        VBox vBox1 = new VBox();
        Label label = new Label("Zadej prikaz");
        TextField uzivatelskyVstup = new TextField();
        ListVychoduComponent listVychodu = new ListVychoduComponent(hra.getHerniPlan());
        ListBatohComponent listBatoh = new ListBatohComponent();
        ListMistnostComponent listMistnost = new ListMistnostComponent();
        AnchorPane ap = pripravMapuHry();
        Scene value = new Scene(parent, 1500, 750);

        listVychodu.setMaxWidth(200);
        listMistnost.setMaxWidth(200);
        listBatoh.setMaxWidth(200);
        listVychodu.setMinHeight(100);

        hBox.getChildren().addAll(label, uzivatelskyVstup);
        vBox1.getChildren().addAll(textArea, hBox);
        vBox.getChildren().addAll(new Label("Vychody"), listVychodu, new Label("Batoh"), listBatoh, new Label("Veci v Mistnosti"), listMistnost);

        parent.setRight(vBox1);
        parent.setLeft(vBox);
        parent.setCenter(ap);

        nastavTextAreaAUzivatelskyVstup(textArea, uzivatelskyVstup, label);

        primaryStage.setScene(value);
        primaryStage.show();
    }

    private AnchorPane pripravMapuHry() {
        MapaHry mapaHry = new MapaHry();
        AnchorPane ap = mapaHry.getAnchorPane();
        return ap;
    }

    private void nastavTextAreaAUzivatelskyVstup(TextArea textArea, TextField vstup, Label label) {
        textArea.setEditable(false);
        textArea.setText(hra.vratUvitani());
        textArea.setMinHeight(700);
        textArea.setMaxWidth(300);
        textArea.setWrapText(true);

        label.setAlignment(Pos.CENTER);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 14));


        vstup.setOnAction(actionEvent -> {
            String prikaz = vstup.getText();
            String output = hra.zpracujPrikaz(prikaz);
            textArea.appendText("\n" + output + "\n" + "\n");
            vstup.setText("");
        });
    }

    public static TextArea getTextArea() {
        return textArea;
    }
}
