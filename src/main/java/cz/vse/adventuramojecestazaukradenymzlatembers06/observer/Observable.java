package cz.vse.adventuramojecestazaukradenymzlatembers06.observer;

public interface Observable {

    void register(Observer observer);
    void unregister(Observer observer);
    void notifyObservers();

}
