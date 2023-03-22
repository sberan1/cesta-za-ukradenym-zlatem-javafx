package cz.vse.adventuramojecestazaukradenymzlatembers06.gui;

import cz.vse.adventuramojecestazaukradenymzlatembers06.logika.HerniPlan;
import cz.vse.adventuramojecestazaukradenymzlatembers06.logika.Prostor;
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

