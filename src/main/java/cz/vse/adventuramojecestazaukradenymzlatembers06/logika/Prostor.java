package cz.vse.adventuramojecestazaukradenymzlatembers06.logika;

import java.text.Normalizer;
import java.util.*;

/**
 * Trida Prostor - popisuje jednotlivé prostory (místnosti) hry
 * Tato třída je součástí jednoduché textové hry.
 * "Prostor" reprezentuje jedno místo (místnost, prostor, ..) ve scénáři hry.
 * Prostor může mít sousední prostory připojené přes východy. Pro každý východ
 * si prostor ukládá odkaz na sousedící prostor.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 * @version pro školní rok 2016/2017
 */
public class Prostor {
    private int counter = 0;
    private String nazev; //nazev mistnosti
    private String popis; //popis mistnosti
    private ArrayList<Prostor> vychody;   // obsahuje sousední místnosti
    private boolean zamceno; //obsahuje informaci o tom, zda je mistnost zamcena
    private List<Vec> veciVMistnosti; //obsahuje seznam veci
    private int modifikatorZivotu; // obsahuje informaci o tom kolik je treba ubrat zivotu pri vstupu do mistnosti
    private HerniPlan plan; //instance herniho planu
    private boolean viditelny; //infromace o tom zda je prostor viditelny
    private int zivotnost; //kolik pruchodu se bude mistnost zobrazovat
    private Vymena vymena; //instance vymeny
    private double posLeft;
    private double posTop;
    /**
     * Vytvoření prostoru se zadaným popisem, např. "kuchyň", "hala", "trávník
     * před domem"
     *
     * @param nazev nazev prostoru, jednoznačný identifikátor, jedno slovo nebo
     * víceslovný název bez mezer.
     * @param popis Popis prostoru.
     */
    public Prostor(String nazev, String popis, HerniPlan plan, double posLeft, double posTop) {
        this.nazev = nazev;
        this.popis = popis;
        vychody = new ArrayList<>();
        veciVMistnosti = new ArrayList<>();
        this.zamceno = false;
        this.modifikatorZivotu = 0;
        this.plan = plan;
        viditelny = true;
        zivotnost = 999;
        vymena = null;
        this.posLeft = posLeft;
        this.posTop = posTop;
    }


    /**
     * Definuje východ z prostoru (sousední/vedlejsi prostor). Vzhledem k tomu,
     * že je použit Set pro uložení východů, může být sousední prostor uveden
     * pouze jednou (tj. nelze mít dvoje dveře do stejné sousední místnosti).
     * Druhé zadání stejného prostoru tiše přepíše předchozí zadání (neobjeví se
     * žádné chybové hlášení). Lze zadat též cestu ze do sebe sama.
     *
     * @param vedlejsi prostor, který sousedi s aktualnim prostorem.
     *
     */
    public void setVychod(Prostor vedlejsi) {
       boolean pomocna = false;
       for(Prostor item : vychody){
           if (item.equals(vedlejsi)) {
               pomocna = true;
               break;
           }
       }
       if (!pomocna){
           vychody.add(vedlejsi);
       }
    }

    /**
     * Vloz veci do mistnosti, se kterymi bude clovek interagovat
     *
     * @param vec vec, ktera se nachazi v mistnosti
     */
    public void vlozVec(Vec vec) {
        veciVMistnosti.add(vec);
    }

    /**
     * Metoda vraci informaci o tom zda tato mistnost obsahuje vec
     *
     * @param vec nazev veci o ktere se ptame zda ji mistnost obsahuje
     * @return true nebo false podle toho zda mistnost vec s timto nazvem obsahuje
     */
    public boolean obsahujeVec(String vec){
        for (Vec neco : veciVMistnosti){
            if (Normalizer.normalize(vec, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").equalsIgnoreCase(neco.getNormalizedNazev())){
                return true;
            }
        }
        return false;
    }

    /**
     * Vytahne vec z mistnosti
     *
     * @param nazev nazev veci, kterou chceme vyndat z mistnosti
     * @return vrati instanci veci kterou chceme vytahnout
     */
    public Vec vyberVeci(String nazev){
        Vec vybranaVec = null;
        for (Vec neco : veciVMistnosti) {
            if (Normalizer.normalize(nazev, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").equalsIgnoreCase(neco.getNormalizedNazev())) {
                vybranaVec = neco;
            }
        }

        if (vybranaVec != null){
            if (vybranaVec.jePrenositelna()){
                veciVMistnosti.remove(vybranaVec);
            }
            else {
                vybranaVec = null;
            }
        }
        return vybranaVec;
    }

    /**
     * Vraci seznam veci v mistnosti jako textovy retezec, ktery je pak mozne vypsat
     * Jedna se o pomocnou metodu
     *
     * @return seznam veci v mistnosti jako textovy retezec
     */
    private String seznamVeci(){
        StringBuilder seznam = new StringBuilder();
        for(Vec neco : veciVMistnosti){
            if (neco.isViditelna()){
            seznam.append(' ').append(neco.getNazev());
            }
        }
        if (seznam.isEmpty()){
            return " Tady nic není";
        }
        return seznam.toString();
    }
    /**
     * Metoda equals pro porovnání dvou prostorů. Překrývá se metoda equals ze
     * třídy Object. Dva prostory jsou shodné, pokud mají stejný název. Tato
     * metoda je důležitá z hlediska správného fungování seznamu východů (Set).
     * Bližší popis metody equals je u třídy Object.
     *
     * @param o object, který se má porovnávat s aktuálním
     * @return hodnotu true, pokud má zadaný prostor stejný název, jinak false
     */  
      @Override
    public boolean equals(Object o) {
        // porovnáváme zda se nejedná o dva odkazy na stejnou instanci
        if (this == o) {
            return true;
        }
        // porovnáváme jakého typu je parametr 
        if (!(o instanceof Prostor)) {
            return false;    // pokud parametr není typu Prostor, vrátíme false
        }
        // přetypujeme parametr na typ Prostor
        Prostor druhy = (Prostor) o;

        //metoda equals třídy java.util.Objects porovná hodnoty obou názvů.
        //Vrátí true pro stejné názvy a i v případě, že jsou oba názvy null,
        //jinak vrátí false.

       return (java.util.Objects.equals(this.nazev, druhy.nazev));       
    }

    /**
     * metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        int vysledek = 3;
        int hashNazvu = java.util.Objects.hashCode(this.nazev);
        vysledek = 37 * vysledek + hashNazvu;
        return vysledek;
    }
      

    /**
     * Vrací normalizovany název prostoru (bez hacku a carek, vsechno malym pro zjednoduseni user inputu)
     *
     * @return normalizovany název prostoru
     */
    public String getnNormalnizedNazev() {
        return  Normalizer
                .normalize(nazev, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "").toLowerCase();
    }
    /**
     * Vrací název prostoru (byl zadán při vytváření prostoru jako parametr
     * konstruktoru)
     *
     * @return název prostoru
     */
    public String getNazev(){
        return nazev;
    }

    /**
     * Vrací "dlouhý" popis prostoru, který obsahuje
     * veci v prostoru, kde se hrac nachazi, pocet zivotu, vychody z prostoru, predmety v batohu a kapacitu batohu
     *
     * @return Dlouhý popis prostoru
     */
    public String dlouhyPopis() {
        SeznamPrikazu seznamPrikazu = plan.getPlatnePrikazy();
        if (plan.isDlouhyVypis()){
            if (getZamceneVychody().equals(""))
            {
            return vypisZacatek()
                    + vypisKonec()
                    + vypisSpecial(seznamPrikazu);
            }
            return vypisZacatek()
                    + "Zamčené východy:" + getZamceneVychody() + "\n"
                    + vypisKonec()
                    + vypisSpecial(seznamPrikazu);

        }
        if (getZamceneVychody().equals(""))
        {
           return vypisZacatek()
                    + vypisKonec();
        }

        return  vypisZacatek() +
                 "Zamčené východy:" + getZamceneVychody() + "\n"
                + vypisKonec();
    }

    private String vypisZacatek(){
       return "Počet životů " + plan.getPocetZivotu() +"/100"+ "\n"
                + "Momentálně se nacházíš v prostoru: " + this.getNazev() + "\n"
                + "Věci v místnosti:" + seznamVeci() + "\n"
                + "Východy:" + getOdemceneVychody() + "\n";
    }
    private String vypisKonec(){
        return "Aktuální předměty v batohu:" + plan.getBatuzek().getPredmetyVBatohu() + "\n"
                + "Kapacita batohu: " + plan.getBatuzek().getVelikostBatuzku() + "/15";
    }

    private String vypisSpecial(SeznamPrikazu seznamPrikazu){
        return "\n" + "Zatim nepouzite prikazy: " + seznamPrikazu.vypisSeznamNepouzitychPrikazu() + "\n"
                + "Zatim neprochazene prostory: " + plan.vypisSeznamNeprojitychMistnosti() + "\n"
                + "Zatim nevidene veci: " + plan.vypisSeznamNepotkanychVeci();

    }
    /**
     * Vrací prostor, který sousedí s aktuálním prostorem a jehož název je zadán
     * jako parametr. Pokud prostor s udaným jménem nesousedí s aktuálním
     * prostorem, vrací se hodnota null.
     *
     * @param nazevSouseda Jméno sousedního prostoru (východu)
     * @return Prostor, který se nachází za příslušným východem, nebo hodnota
     * null, pokud prostor zadaného jména není sousedem.
     */
    public Prostor vratSousedniProstor(String nazevSouseda) {
        List<Prostor>hledaneProstory =
                vychody.stream()
                        .filter(sousedni -> sousedni.getnNormalnizedNazev().equals(nazevSouseda)).toList();
        if(hledaneProstory.isEmpty()){
            return null;
        }
        else {
            return hledaneProstory.get(0);
        }
    }

    /**
     * Vraci textovy retezec s odemcenymi vychody
     *
     * @return textovy retezec obsahujici odemcene vychody
     */
    public String getOdemceneVychody() {
        StringBuilder pomoc = new StringBuilder();
        for (var item : vychody){
            if (!item.getStav() && item.zivotnost > 0 && item.isViditelny()){
            pomoc.append(' ').append(item.getNazev());
            }
        }
        if (vychody.isEmpty()){
            return " Odsud jentak neutečeš.";
        }
        return pomoc.toString();
    }

    /**
     * Vraci textovy retezec se zamcenymi vychody
     *
     * @return textovy retezec obsahujici zamcene vychody
     */
    public String getZamceneVychody() {
        StringBuilder pomoc = new StringBuilder();
        for (var item : vychody){
            if (item.getStav() && item.zivotnost > 0 && item.isViditelny()){
                pomoc.append(' ').append(item.getNazev());
            }
        }
        return pomoc.toString();
    }

    /**
     * Vraci stav mistnosti, respektive informaci o tom zda je mistnost zamcena
     *
     * @return t/f jestli je mistnost zamcena
     */
    public boolean getStav() {
        return zamceno;
    }

    /**
     * funkce odemkne mistnost
     */
    public void odemknoutMistnost() {
        if (this.zamceno){
            zamceno = false;
        }
    }

    /**
     * funkce zamkne mistnost
     */
    public void zamknoutMistnost() {
    if (!this.zamceno){
        zamceno = true;
    }
    }

    /**
     * nastavuje modifikator zivotu pri vkroceni do mistnosti
     *
     * @param modifikatorZivotu ciselna hodnota kolik ubrat hraci po vstupu do mistnosti
     */
    public void setModifikatorZivotu(int modifikatorZivotu) {
        this.modifikatorZivotu = modifikatorZivotu;
    }

    /**
     * Vraci integer s hodnotou modifikatoru zivotu
     *
     * @return hodnotu modifkatoru zivotu
     */
    public int getModifikatorZivotu() {
        return modifikatorZivotu;
    }

    /**
     * Vrací kolekci obsahující prostory, se kterými tento prostor sousedí.
     * Takto získaný seznam sousedních prostor nelze upravovat (přidávat,
     * odebírat východy) protože z hlediska správného návrhu je to plně
     * záležitostí třídy Prostor.
     *
     * @return Nemodifikovatelná kolekce prostorů (východů), se kterými tento
     * prostor sousedí.
     */
    public Collection<Prostor> getVychody() {
        return Collections.unmodifiableCollection(vychody);
    }

    /**
     * Vraci popis mistnoti nastaveny konstruktorem
     *
     * @return textovy retezec s popisem mistnosti
     */
    public String getPopis() {
        return popis;
    }

    /**
     * Vraci informaci o tom zda je mistnost zamcena
     *
     * @return t/f informace o stavu mistnosti
     */

    public boolean isZamceno() {
        return zamceno;
    }

    /**
     * Vraci informaci o tom zda je mistnost viditelna
     *
     * @return t/f informace o stavu mistnosti
     */
    public boolean isViditelny() {
        return viditelny;
    }

    /**
     * Moznost nastavit viditelnost mistonosti pomoci parametru
     *
     * @param viditelnost t/f hodnota s informaci o tom, zda chceme nebo nechceme mistnost videt
     */
    public void setViditelnost(boolean viditelnost) {
        this.viditelny = viditelnost;
    }

    /**
     * Vraci novou kolekci se sousednimi prostory k instanci, ktere nejsou videt
     *
     * @return List plny neviditelnych prostoru
     */
    public List<Prostor> schovaneProstory(){
        ArrayList<Prostor> mistni = new ArrayList<>();
        for (var item : vychody){
            if (!item.isViditelny()){
                mistni.add(item);
            }
        }
        return mistni;
    }

    /**
     * Vraci List s vecmi, ktere v mistnosti nejdou videt
     *
     * @return List s vecmi, ktere v mistnosti nejdou videt
     */
    public List<Vec> getSchovaneVeci() {
        ArrayList<Vec> mistniList = new ArrayList<>();
        for (var item : veciVMistnosti){
            if (!item.isViditelna()){
                mistniList.add(item);
            }
        }
        return mistniList;
    }

    /**
     * prida vecem v mistnosti jedno videni
     */
    public void videtVeciVMistnosti() {
        for (var item : veciVMistnosti){
            if (item.isViditelna()){
                item.pridatVideniVeci();
            }
        }
    }

    /**
     * Vraci novou kolekci se sousednimi prostory k instanci, ktere jsou zamcene
     *
     * @return List plny zamcenych prostoru
     */
    public ArrayList<Prostor> zamceneProstoryList(){
        ArrayList<Prostor> mistni = new ArrayList<>();
        for (var item : vychody){
            if (item.getStav()) {
                mistni.add(item);
            }
        }
        return mistni;
    }

    /**
     * Dovoluje nastavit zivotnost mistnosti, tj. kolikrat bude mistnost videt mezi sousednimi pri pruchodu mistnostmi
     *
     * @param zivotnost ciselna hodnota kolikrat bude mistnost videt (nutno zadat hodnotu o jedna vetsi)
     */
    public void nastavPast(int zivotnost){
        this.zivotnost = zivotnost;
    }

    /**
     * vraci ciselnou hodnotu s zivotnosti mistnoti
     *
     * @return zivotnost mistnosti
     */
    public int getZivotnost() {
        return zivotnost;
    }

    /**
     * Vraci instanci tridy Vymena, ktera je v teto mistnosti
     *
     * @return vymena dostupna v mistnosti
     */
    public Vymena getVymena() {
        return vymena;
    }

    /**
     * Nastaveni hodnoty vlastnosti vymena, aneb Vymena ktera jde v mistnosti provest
     *
     * @param vymena vlozeni instance tridy vymena, ktera pujde v mistnosti provest
     */
    public void setVymena(Vymena vymena) {
        this.vymena = vymena;
    }

    /**
     * Vraci pocet pruchodu mistnosti
     *
     * @return pocet pruchodu
     */
    public int getCounter() {
        return counter;
    }

    /**
     * prida pruchod
     */
    public void pridatPruchod() {
        counter++;
    }


    public double getPosLeft() {
        return posLeft;
    }

    public double getPosTop() {
        return posTop;
    }
}
