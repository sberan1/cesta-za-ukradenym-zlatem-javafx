module cz.vse.adventuramojecestazaukradenymzlatembers06 {
    requires javafx.controls;
    requires javafx.fxml;


    opens cz.vse.adventuramojecestazaukradenymzlatembers06.main to javafx.fxml;
    exports cz.vse.adventuramojecestazaukradenymzlatembers06.main;
}