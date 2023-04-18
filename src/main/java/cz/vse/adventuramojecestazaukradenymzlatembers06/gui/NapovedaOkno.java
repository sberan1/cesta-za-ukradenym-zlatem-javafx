package cz.vse.adventuramojecestazaukradenymzlatembers06.gui;

import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import java.net.URL;

/**
 * Třída pro vytvoření okna s nápovědou, které se zobrazí po stisknutí tlačítka "Nápověda".
 *
 * @author sberan1
 */
public class NapovedaOkno extends Stage {

    /**
     * Konstruktor třídy pro vytvoření okna s nápovědou, které se zobrazí po stisknutí tlačítka "Nápověda".
     * Obsahuje odkaz na HTML soubor s nápovědou.
     */
        public NapovedaOkno() {
            WebView webView = new WebView();
            WebEngine engine = webView.getEngine();
            URL url = getClass().getResource("NavodNaHrani.html");
            engine.load(url.toExternalForm());

            Scene scene = new Scene(webView, 800, 1000);
            this.setScene(scene);
        }
}
