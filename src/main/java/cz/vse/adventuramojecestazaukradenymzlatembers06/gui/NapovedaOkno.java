package cz.vse.adventuramojecestazaukradenymzlatembers06.gui;

import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import java.net.URL;

public class NapovedaOkno extends Stage {

        public NapovedaOkno() {
            WebView webView = new WebView();
            WebEngine engine = webView.getEngine();
            URL url = getClass().getResource("NavodNaHrani.html");
            engine.load(url.toExternalForm());

            Scene scene = new Scene(webView, 800, 1000);
            this.setScene(scene);
        }
}