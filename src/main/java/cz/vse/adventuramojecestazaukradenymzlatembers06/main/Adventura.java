package cz.vse.adventuramojecestazaukradenymzlatembers06.main;

import cz.vse.adventuramojecestazaukradenymzlatembers06.gui.*;
import cz.vse.adventuramojecestazaukradenymzlatembers06.logika.Hra;
import cz.vse.adventuramojecestazaukradenymzlatembers06.logika.IHra;
import cz.vse.adventuramojecestazaukradenymzlatembers06.uiText.TextoveRozhrani;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.File;


public class Adventura extends Application {
    Hra hra = Hra.getSingleton();
    private static TextArea textArea = new TextArea();
    ListVychoduComponent listVychodu = new ListVychoduComponent();
    ListBatohComponent listBatoh = new ListBatohComponent();
    ListMistnostComponent listMistnost = new ListMistnostComponent();
    MapaHry mapaHry = new MapaHry();

    /**
     * Spustí požadovanou verzi adventury podle argumentů v příkazové řádce
     *
     * @param args argumenty příkazové řádky
     */
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-text")) {
            TextoveRozhrani.main(args);
        } else {
            Adventura.launch(args);
        }
    }

    /**
     *  Metoda která se volá při spuštění aplikace, nastavuje scénu a zobrazuje ji
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(@NotNull Stage primaryStage) {
        BorderPane parent = new BorderPane();
        HBox hBox = new HBox();
        VBox vBox = new VBox();
        VBox vBox1 = new VBox();
        Label label = new Label("Zadej prikaz");
        TextField uzivatelskyVstup = new TextField();
        Scene value = new Scene(parent, 1500, 800);
        MenuBar menuBar = new MenuBar();
        Menu menuHra = new Menu("Hra");
        Menu menuNapoveda = new Menu("Napoveda");
        nastavMenu(parent, menuBar, menuHra, menuNapoveda, primaryStage);

        listVychodu.setMaxWidth(200);
        listMistnost.setMaxWidth(200);
        listBatoh.setMaxWidth(200);
        listVychodu.setMinHeight(100);

        hBox.getChildren().addAll(label, uzivatelskyVstup);
        vBox1.getChildren().addAll(textArea, hBox);
        vBox.getChildren().addAll(new Label("Vychody"), listVychodu, new Label("Batoh"), listBatoh, new Label("Veci v Mistnosti"), listMistnost);

        parent.setRight(vBox1);
        parent.setLeft(vBox);
        parent.setCenter(mapaHry);

        nastavTextAreaAUzivatelskyVstup(textArea, uzivatelskyVstup, label);

        primaryStage.setScene(value);
        primaryStage.show();
    }

    /**
     * Nastaví textArea a uzivatelskyVstup tak, aby se daly používat v aplikaci
     *
     * @param parent borderPane, který obsahuje to jak hra vypadá
     * @param menuBar menuBar, který obsahuje menu, ktere budeme upravovat
     * @param menuHra menu, ktere obsahuje moznosti hry
     * @param menuNapoveda menu, ktere obsahuje napovedu
     * @param primaryStage hlavni okno aplikace
     */

    private void nastavMenu(BorderPane parent, MenuBar menuBar, Menu menuHra, Menu menuNapoveda, Stage primaryStage) {
        MenuItem novaHra = new MenuItem("Nova hra");
        MenuItem konecHry = new MenuItem("Konec hry");
        MenuItem oProgramu = new MenuItem("O programu");
        MenuItem ulozitHru = new MenuItem("Ulozit hru");
        MenuItem nacistHru = new MenuItem("Nacist hru");

        novaHra.addEventHandler(javafx.event.ActionEvent.ACTION, event -> {
            hra = Hra.restartHry();
            hra.getHerniPlan().register(listVychodu);
            hra.getHerniPlan().register(listMistnost);
            hra.getHerniPlan().register(mapaHry);
            hra.getHerniPlan().getBatuzek().register(listBatoh);
            hra.getHerniPlan().getBatuzek().register(listMistnost);
            hra.getHerniPlan().getAktualniProstor().register(listMistnost);
            hra.getHerniPlan().notifyObservers();
            hra.getHerniPlan().getBatuzek().notifyObservers();
            hra.getHerniPlan().getAktualniProstor().notifyObservers();
            textArea.clear();
            textArea.setText(hra.vratUvitani());
        });

        konecHry.addEventHandler(javafx.event.ActionEvent.ACTION, event -> {
            System.exit(0);
        });

        oProgramu.addEventHandler(javafx.event.ActionEvent.ACTION, event -> {
            NapovedaOkno napovedaOkno = new NapovedaOkno();
            napovedaOkno.show();
        });

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.txtsave"));

        ulozitHru.addEventHandler(javafx.event.ActionEvent.ACTION, event -> {
            fileChooser.setTitle("Ulož hru");
            File saveFile = fileChooser.showSaveDialog(primaryStage);
            if (saveFile != null) {
                hra.ulozHru(saveFile.getAbsolutePath());
            }
        });

        nacistHru.addEventHandler(javafx.event.ActionEvent.ACTION, event -> {
            fileChooser.setTitle("Načti hru");
            File loadFile = fileChooser.showOpenDialog(primaryStage);
            if (loadFile != null) {
                hra.nactiHru(loadFile.getAbsolutePath());
            }
        });

        menuHra.getItems().addAll(novaHra, konecHry, ulozitHru, nacistHru);
        menuHra.getItems().add(2, new SeparatorMenuItem());
        menuNapoveda.getItems().add(oProgramu);
        menuBar.getMenus().addAll(menuHra, menuNapoveda);
        parent.setTop(menuBar);
    }


    /**
     * Nastaví textArea a uzivatelskyVstup tak, aby se daly používat v aplikaci.
     *
     * @param textArea textArea, do kterého se vypisují výstupy z hry
     * @param vstup textové pole, do kterého uživatel zadává příkazy
     * @param label label, který se zobrazuje nad textovým polem
     */
    private void nastavTextAreaAUzivatelskyVstup(@NotNull TextArea textArea, @NotNull TextField vstup, @NotNull Label label) {
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

    /**
     * Vrací textArea, do kterého se vypisují výstupy z hry
     *
     * @return textArea
     */
    public static TextArea getTextArea() {
        return textArea;
    }

}
