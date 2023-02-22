package cz.vse.adventuramojecestazaukradenymzlatembers06.logika;

import java.util.ArrayList;
import java.util.List;

/**
 * Trida Vymena - trida pro realizaci vymen, ktera nam dovoluje vytvaret v kodu vymeny
 *
 * @author sBeran1
 */
public class Vymena {
    private String popisVymeny; //popis ktery se zobrazi hraci pri pouziti prikazu Vymeny
    private ArrayList<Vec> ocekavaneVeci; //list veci ktere prikaz prijma
    private ArrayList<INavrat> navratoveHodnoty; //list objektu, ktere budou navrat
    private String kratkyNazev; //kratky nazev pouzivany k vyvolani prikazu
    private Object trestZaNesplneni; //co se ma stat, pokud hrac nebude mit dostupne objekty
    private String textKZobrazeni; //text k zobrazeni po uspesne vymene
    private Prostor[] odemceniMistnosti; //dvourozmerne pole obsahujici v jake mistnosti se ma zobrazit jaka mistnost jako sousedni

    /**
     * Konstruktor tridy
     *
     * @param popisVymeny text o vymene obsahujici zakladni informace
     * @param kratkyNazev nazev pro vyvolani vymeny prikazem vymena
     * @param textKZobrazeni text po uspesnem spleneni vymeny
     */
    public Vymena(String popisVymeny, String kratkyNazev, String textKZobrazeni) {
        this.popisVymeny = popisVymeny;
        this.kratkyNazev = kratkyNazev;
        this.textKZobrazeni = textKZobrazeni;
        this.trestZaNesplneni = null;
        odemceniMistnosti = new Prostor[2];
        ocekavaneVeci = new ArrayList<>();
        navratoveHodnoty = new ArrayList<>();
    }

    /**
     * metoda pro pridani ocekavane veci do Listu ocekavanych veci
     *
     * @param ocekavanaVec Vec, kterou chceme vlozit do listu ocekavanych veci
     */

    public void setOcekavaneVeci(Vec ocekavanaVec) {
        ocekavaneVeci.add(ocekavanaVec);
    }

    /**
     * metoda pro pridani vraceneho Objektu do Listu vracenych objektu
     *
     * @param navratovaHodnota Objekt, ktery chceme vlozit do listu vracenych objektu
     */
    public void setNavratovaHodnota(INavrat navratovaHodnota) {
        navratoveHodnoty.add(navratovaHodnota);
    }

    /**
     * Vraci naklonovany seznam ocekavanych veci
     *
     * @return kopii listu s ocekavanymi vecmi
     */
    public List<Vec> getOcekavaneVeci() {
        return (ArrayList<Vec>) ocekavaneVeci.clone();
    }

    /**
     * Vraci naklonovany seznam vracenych objektu
     *
     * @return kopii listu s vracenymi objekty
     */
    public List<INavrat> getNavratoveHodnoty() {
        return (ArrayList<INavrat>) navratoveHodnoty.clone();
    }

    /**
     * Vraci kratky nazev, kterym volame vymenu
     *
     * @return string s nazvem vymeny
     */
    public String getKratkyNazev() {
        return kratkyNazev;
    }

    /**
     * metoda pro nastaveni trestu za nesplneni
     *
     * @param trestZaNesplneni Objekt kterym potrestame hrace za pokus o vymenu bez vlastneni vsech veci
     */
    public void setTrestZaNesplneni(Object trestZaNesplneni) {
        this.trestZaNesplneni = trestZaNesplneni;
    }
    
    /**
     * Vraci Objekt, ktery bude trest za nespleneni
     *
     * @return trest za nespleni
     */
    public Object getTrestZaNesplneni() {
        return trestZaNesplneni;
    }

    /**
     * Vraci zpravu za uspesnou vymenu
     *
     * @return text k zobrazeni po uspesne vymene
     */
    public String getTextKZobrazeni() {
        return textKZobrazeni;
    }

    /**
     * nastaveni pridani vychodu jako odmenu za vymenu
     *
     * @param kdePridatMistnost mistnost ktere pridame vychod
     * @param jakouMistnostPridat mistnost kterou pridame prvni mistnosti jako vychod
     */
    public void setOdemceniMistnosti(Prostor kdePridatMistnost, Prostor jakouMistnostPridat) {
        odemceniMistnosti[0] = kdePridatMistnost;
        odemceniMistnosti[1] = jakouMistnostPridat;
    }

    /**
     * Vraci pole s odemcenim mistnosti
     *
     * @return pole s odemcenim mistnosti
     */
    public Prostor[] getOdemceniMistnosti() {
        return odemceniMistnosti;
    }

    /**
     * Prepracovani metody toString vlastni vsem objektum
     *
     * @return upraveny textovy retezec s navodem jak vyvolat vymenu
     */
    @Override
    public String toString() {
        return popisVymeny + "\n pokud mas vsechny predmety tak to aktivujes napsanim vymen " + kratkyNazev ;
    }
}
