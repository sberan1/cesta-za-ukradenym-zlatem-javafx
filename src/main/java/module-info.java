module cz.vse.adventuramojecestazaukradenymzlatembers06 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jetbrains.annotations;
    requires javafx.web;


    opens cz.vse.adventuramojecestazaukradenymzlatembers06.main to javafx.fxml;
    exports cz.vse.adventuramojecestazaukradenymzlatembers06.main;
}