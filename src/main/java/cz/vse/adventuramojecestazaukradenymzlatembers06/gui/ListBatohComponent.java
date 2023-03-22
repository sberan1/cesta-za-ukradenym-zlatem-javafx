package cz.vse.adventuramojecestazaukradenymzlatembers06.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import cz.vse.adventuramojecestazaukradenymzlatembers06.logika.Hra;
import cz.vse.adventuramojecestazaukradenymzlatembers06.observer.Observer;

public class ListBatohComponent extends ListView<String> implements Observer {

    private Hra hra = Hra.getSingleton();
    private ObservableList<String> itemsList;

    public ListBatohComponent() {
        hra.getHerniPlan().getBatuzek().register(this);

        itemsList = FXCollections.observableArrayList();
        setItems(itemsList);

        update();
    }




    @Override
    public void update() {
        itemsList.clear();
        hra.getHerniPlan().getBatuzek().getObsah().forEach(vec -> itemsList.add(vec.getNazev()));
        }
    }

