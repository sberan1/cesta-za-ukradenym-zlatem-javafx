package cz.vse.adventuramojecestazaukradenymzlatembers06.logika;

import cz.vse.adventuramojecestazaukradenymzlatembers06.observer.Observable;
import cz.vse.adventuramojecestazaukradenymzlatembers06.observer.Observer;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Class Batoh - trida pro realizaci batohu na uchovavani predmetu
 *
 * @author sBeran1
 */
public class Batoh implements Observable {
    private int velikostBatuzku; //nastavuje pocet veci co se vejde do batohu
    private ArrayList<Vec> obsah; //list s vecmi co se nachazi v batohu
    private Set<Observer> observers = new HashSet<>();

    /**
     * konstruktor pro zalozeni instance kosicku
     *
     * @param velikostKosicku - nastavuje pocet veci co se vejdou do batohu
     */
    public Batoh(int velikostKosicku) {
        this.velikostBatuzku = velikostKosicku;
        obsah = new ArrayList<>();
    }
    /**
     * funkce pro vlozeni instance veci do batohu
     *
     * @return - vraci true/false jestli bylo vlozeni veci uspesne
     */
    public boolean vlozVec(Vec neco){
        if(obsah.size() < velikostBatuzku){
            obsah.add(neco);
            notifyObservers();
            return true;
        }
            return false;

    }

    /**
     * Odebere vec z batohu podle nazvu, prijma nazev, vraci t/f
     *
     * @param nazev - nazev veci v jakemokoliv formatu
     * @return - true nebo false podle uspesnosti odebrani veci
     */
    public boolean odeberVec(String nazev){
        String normalizedNazev = Normalizer.normalize(nazev, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase();
        for (var item : obsah){
            if (normalizedNazev.equals(item.getNormalizedNazev())){
                obsah.remove(item);
                notifyObservers();
                return true;
            }
        }
        return false;
    }
    /**
     * Vraci ciselnou hodnotu s velikosti batuzku
     *
     * @return - velikost batohu
     */
    public int getVelikostBatuzku() {
        return obsah.size();
    }
    public int getMaximalniVelikostBatuzku() {
        return velikostBatuzku;
    }

    /**
     * Vraci textovy         retezec s nazvama veci
     *
     * @return - text s nazvy predmetu v batohu
     */
    public String getPredmetyVBatohu(){
        StringBuilder predmety = new StringBuilder();
        for (var item : obsah) {
            predmety.append(' ').append(item.getNazev());
        }
        if (predmety.toString().equals("")){
            return " V batohu nemáš nic";
        }
        return predmety.toString();
    }

    /**
     * Vraci naklonovany List s vecmi v batohu
     *
     * @return - kopii listu s obsahem batohu
     */
    public ArrayList<Vec> getObsah(){
        return (ArrayList<Vec>) obsah.clone();
    }

    /**
     * metoda pro registraci observeru
     *
     * @param observer - observer ktery se ma registrovat
     */
    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    /**
     * metoda pro odregistrovani observeru
     */
    @Override
    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    /**
     * metoda pro upozorneni observeru
     */
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }

    }
}
