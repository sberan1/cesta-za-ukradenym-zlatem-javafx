package cz.vse.adventuramojecestazaukradenymzlatembers06.gui;

import cz.vse.adventuramojecestazaukradenymzlatembers06.logika.HerniPlan;
import cz.vse.adventuramojecestazaukradenymzlatembers06.logika.Hra;
import cz.vse.adventuramojecestazaukradenymzlatembers06.logika.Prostor;
import cz.vse.adventuramojecestazaukradenymzlatembers06.main.Adventura;
import cz.vse.adventuramojecestazaukradenymzlatembers06.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class ListVychoduComponent extends ListView<String> implements Observer {

    private HerniPlan herniPlan;
    private ObservableList<String> itemsList;

    public ListVychoduComponent(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
        herniPlan.register(this);

        itemsList = FXCollections.observableArrayList();
        setItems(itemsList);

        setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2) {
                String selectedItem = getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    String odpoved = Hra.getSingleton().zpracujPrikaz("jdi " + selectedItem);
                    Adventura.getTextArea().appendText("\n" + odpoved + "\n");
                    System.out.println("clicked on " + selectedItem + "!");
                }
            }
        });

        update();
    }




    @Override
    public void update() {
        itemsList.clear();
        for (Prostor prostor : herniPlan.getAktualniProstor().getVychody()) {
            if (prostor.isZamceno()){
                itemsList.add(prostor.getNazev() + " 🔒");
            }
            else {
                itemsList.add(prostor.getNazev());
            }
        }
    }
    }

