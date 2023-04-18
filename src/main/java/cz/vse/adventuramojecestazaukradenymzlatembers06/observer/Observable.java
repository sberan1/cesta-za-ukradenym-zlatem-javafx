package cz.vse.adventuramojecestazaukradenymzlatembers06.observer;

public interface Observable {
    /**
     * Metody pro registraci, odregistraci a notifikaci observeru
     *
     * @param observer - observer ktery se ma registrovat
     */
    void register(Observer observer);

    /**
     * Metoda pro odregistrovani observeru
     *
     * @param observer - observer ktery se ma odregistrovat
     */
    void unregister(Observer observer);
    /**
     * Metoda pro notifikaci observeru
     */
    void notifyObservers();

}
