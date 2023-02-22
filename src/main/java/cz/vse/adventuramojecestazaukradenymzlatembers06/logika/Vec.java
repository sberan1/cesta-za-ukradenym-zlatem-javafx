package cz.vse.adventuramojecestazaukradenymzlatembers06.logika;

import java.text.Normalizer;
/**
 * Trida Vec - trida pro realizaci veci, ktere muzeme najit v mistnostech a ukladat je do batohu a prenaset je
 *
 * @author sBeran1
 */
public class Vec {
    private int counter = 0; //pocita, kolikrat jsi jednotlive instance videl
    private String nazev; //nazev veci ktery se bude zobrazovat a se kterym budeme v programu pracovat

    private boolean prenositelna; //nastavuje prenositelnost nebo-li moznost ulozit si vec do batohu

    private boolean viditelna; //viditelnost veci v mistnosti, pouzivano pro prikaz prozkoumej

    private boolean pouzitelna; //pouzitelnost veci a funkce pro dva konstruktory
    private Pouzitelnosti typ; //volba z Enumu Pouzitelnosti pro typ pouzitelne veci
    private int modifikator; //modifikator kolik dane vlastnosti ma predmet pridat/ubrat

    /**
     * Konstruktor pro vytvoreni pouzitelne veci, pomaha nam zakladat instance pouzitelnych veci
     *
     * @param nazev - nazev veci
     * @param prenositelna - prenositelnost veci
     * @param viditelna - zakladni viditelnost veci v mistnosti
     * @param typ - typ veci z Enumu Pouzitelnosti
     * @param modifikator - ciselna hodnota kolik ma vec ubrat/pridat
     */
    public Vec(String nazev, boolean prenositelna, boolean viditelna, Pouzitelnosti typ, int modifikator) {
        this.nazev = nazev;
        this.prenositelna = prenositelna;
        this.viditelna = viditelna;
        pouzitelna = true;
        this.typ = typ;
        this.modifikator = modifikator;
    }

    /**
     * Konstruktor pro vytvoreni nepouzitelne veci, pomaha nam zakladat instance nepouzitelnych veci
     *
     * @param nazev - nazev veci
     * @param prenositelnost - prenositelnost veci
     * @param viditelnost - zakladni viditelnost veci v mistnosti
     */

    public Vec(String nazev, boolean prenositelnost, boolean viditelnost) {
        this.nazev = nazev;
        this.prenositelna = prenositelnost;
        this.viditelna = viditelnost;
        pouzitelna = false;
        typ = null;
    }

    /**
     * Vraci nazev veci
     *
     * @return nazev veci
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * Vraci prenositelnost predmetu
     *
     * @return prenositelnost veci
     */
    public boolean jePrenositelna() {
        return prenositelna;
    }

    /**
     * Vraci viditelnost predmetu, respektive informaci o tom, zda je predmet v mistnosti schovany, nebo je videt na prvni pohled.
     *
     * @return viditelnost veci
     */
    public boolean isViditelna() {
        return viditelna;
    }

    /**
     * Dovoluje nam nastavit viditelnost predmetu v mistnosti
     *
     * @param viditelna - parametr urcujici jestli ma byt predmet viditelny v mistnosti
     */
    public void setViditelna(boolean viditelna) {
        this.viditelna = viditelna;
    }

    /**
     * Vraci normalizovany nazev veci pro lehci a intuitivnejsi pouziti aplikace
     *
     * @return - normalizovany nazev bez hacku a carek a vsechno malyma pismenama
     */
    public String getNormalizedNazev(){
        return Normalizer.normalize(nazev, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").toLowerCase();
    }

    /**
     * Vraci true/false hodnotu urcujici pouzitelnost veci
     *
     * @return - pouzitelnost veci, ktera je urcena volbou konstruktoru
     */
    public boolean isPouzitelna() {
        return pouzitelna;
    }

    /**
     * Vraci urcenou hodnotu z enumu pouzitelnosti
     *
     * @return - Typ veci z enumu pouzitelnosti
     */
    public Pouzitelnosti getTyp() {
        return typ;
    }

    /**
     * Vraci modifikator pouzitelne veci nebo 0 pokud se nejedna o pouzitelnou vec
     *
     * @return - ciselnou hodnota modifikatoru
     */
    public int getModifikator() {
        if (pouzitelna) {
            return modifikator;
        }
        else{
            return 0;
        }
    }

    /**
     * Vraci pocet videni veci
     *
     * @return videni veci
     */
    public int getCounter() {
        return counter;
    }

    /**
     * prida videni
     */
    public void pridatVideniVeci() {
        counter++;
    }
}
