package cz.vse.adventuramojecestazaukradenymzlatembers06.logika;

import java.text.Normalizer;
import java.util.ArrayList;

/**
 * Class Batoh - trida pro realizaci batohu na uchovavani predmetu
 *
 * @author sBeran1
 */
public class Batoh {
    private int velikostBatuzku; //nastavuje pocet veci co se vejde do batohu
    private ArrayList<Vec> obsah; //list s vecmi co se nachazi v batohu

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
}
