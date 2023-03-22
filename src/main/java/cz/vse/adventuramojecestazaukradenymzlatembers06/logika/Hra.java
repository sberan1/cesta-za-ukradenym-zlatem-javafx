package cz.vse.adventuramojecestazaukradenymzlatembers06.logika;



import cz.vse.adventuramojecestazaukradenymzlatembers06.observer.Observable;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */

public class Hra implements IHra {
    private HerniPlan herniPlan; //obsahuje instanci herniho plan
    private static boolean konecHry = false; //nastavuje konec hry
    private String epilog = "Dohrál jsi tuto úžasnou hru, našel jsi ukradené zlato a je už jen na tobě, jestli si ho necháš, nebo ho půjdeš vrátit do města. Děkuji za zahrání!";
    private List<String> pouzitePrikazy;
    private static Hra singleton = new Hra();

    /**
     * Podle navrhoveho vzoru Singleton (GoF).
     */
    public static Hra getSingleton(){
        return singleton;
    }

    public static Hra restartHry() {
        singleton = new Hra();
        return singleton;
    }
    /**
     * Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    private Hra() {
        herniPlan = new HerniPlan();
        herniPlan.getPlatnePrikazy().vlozPrikaz(new PrikazNapoveda(herniPlan.getPlatnePrikazy()));
        herniPlan.getPlatnePrikazy().vlozPrikaz(new PrikazJdi(this));
        herniPlan.getPlatnePrikazy().vlozPrikaz(new PrikazKonec(this));
        herniPlan.getPlatnePrikazy().vlozPrikaz(new PrikazSeber(herniPlan));
        herniPlan.getPlatnePrikazy().vlozPrikaz(new PrikazProzkoumej(herniPlan));
        herniPlan.getPlatnePrikazy().vlozPrikaz(new PrikazOdemkni(herniPlan));
        herniPlan.getPlatnePrikazy().vlozPrikaz(new PrikazPoloz(herniPlan));
        herniPlan.getPlatnePrikazy().vlozPrikaz(new PrikazVymen(herniPlan));
        herniPlan.getPlatnePrikazy().vlozPrikaz(new PrikazVymeny(herniPlan));
        herniPlan.getPlatnePrikazy().vlozPrikaz(new PrikazPouzij(herniPlan));
        herniPlan.getPlatnePrikazy().vlozPrikaz(new PrikazUloz(this));
        herniPlan.getPlatnePrikazy().vlozPrikaz(new PrikazNacti(this));
        pouzitePrikazy = new ArrayList<>();
    }

    /**
     * Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "Vítej v adventuře, kde je tvým cílem dojít do zamčené " +
                "shované místnosti v čarodějově věži kde čaroděj shovává všechno ukradené zlato.\n" +
                "Čeká tě těžký průchod a budeš muset cestou posbírat několik předmětů.\n " +
                "Napište 'nápověda', pokud si nevíte rady, jak hrát dál. \n" +
                herniPlan.getAktualniProstor().dlouhyPopis();
    }

    /**
     * Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return epilog;
    }

    /**
     * Vrací true, pokud hra skončila.
     */
    public boolean konecHry() {
        return konecHry;
    }

    /**
     * Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     * Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     * Pokud ano spustí samotné provádění příkazu.
     *
     * @param radek text, který zadal uživatel jako příkaz do hry.
     * @return vrací se řetězec, který se má vypsat na obrazovku
     */
    public String zpracujPrikaz(String radek) {
        if (!radek.equals("ulož")){
        pouzitePrikazy.add(radek);
        }
        String[] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String[] parametry = new String[slova.length - 1];
        for (int i = 0; i < parametry.length; i++) {
            parametry[i] = slova[i + 1];
        }
        String textKVypsani;
        if (herniPlan.getPlatnePrikazy().jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = herniPlan.getPlatnePrikazy().vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry);
        } else {
            textKVypsani = "Nevím co tím myslíš? Tento příkaz neznám. ";
        }
        if (herniPlan.getPocetZivotu() <= 0) {
            konecHry = true;
            return "prohrals!";
        }
        return textKVypsani;
    }

    // save the game
    public void ulozHru(String filePath) {
        try {
            FileWriter writer = new FileWriter(filePath, false);
            for (String action : pouzitePrikazy) {
                writer.write(action + "\n");
            }
            writer.close();
        } catch (IOException e) {
            // handle exception
        }
    }

    // load the game
    public void nactiHru(String filePath) {
        try {
            pouzitePrikazy = new ArrayList<>();
            FileReader reader = new FileReader(filePath);
            StringBuilder str = new StringBuilder();
            int ch;
            while ((ch = reader.read()) != -1) {
                str.append((char) ch);
            }
            reader.close();
            String[] actionStrings = str.toString().split("\n");
            for (String actionString : actionStrings) {
                zpracujPrikaz(actionString);
            }
        } catch (IOException e) {
            // handle exception
        }
    }

    
    
     /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
     /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
     public HerniPlan getHerniPlan(){
        return herniPlan;
     }

    /**
     * nastavi epilog
     *
     * @param epilog - text co ma hra vratit pri ukonceni hry
     */

    public void setEpilog(String epilog) {
        this.epilog = epilog;
    }


}

