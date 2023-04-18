package cz.vse.adventuramojecestazaukradenymzlatembers06.logika;

/**
 *  Třída PrikazKonec implementuje pro hru příkaz konec.
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *@author     Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 *  
 */

public class PrikazKonec implements IPrikaz {

    private int counter = 0; //pocita pouziti prikazu
    private static final String NAZEV = "konec"; //nazev prikazu a jeho zneni ve hre

    private Hra hra; //instance trida hra
    boolean viditelnost = true;


    /**
     *  Konstruktor třídy
     *  
     *  @param hra odkaz na hru, která má být příkazem konec ukončena
     */    
    public PrikazKonec(Hra hra) {
        this.hra = hra;
    }

    /**
     * V případě, že příkaz má jen jedno slovo "konec" hra končí(volá se metoda setKonecHry(true))
     * jinak pokračuje např. při zadání "konec a".
     * 
     * @return zpráva, kterou vypíše hra hráči
     */

    @Override
    public String provedPrikaz(String... parametry) {
        counter++;
        if (parametry.length > 0) {
            return "Ukončit co? Nechápu, proč jste zadal druhé slovo.";
        }
        else {
            hra.setKonecHry(true);
            return "hra ukončena příkazem konec";
        }
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

    /**
     * Vraci ciselnou hodnotu s poctem pouziti prikazu, pouzivano pro statistiky a nove vypisy
     *
     * @return pocet pouziti prikazu
     */
    @Override
    public int getCounter() {
        return counter;
    }

    @Override
    public boolean isViditelny() {
        return viditelnost;
    }


}
