package cz.vse.adventuramojecestazaukradenymzlatembers06.logika;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestyTrid {

    public Hra hra;
    @BeforeEach
    void setUp() {
        hra = Hra.getSingleton();
    }

    @AfterEach
    void tearDown() {
        hra = Hra.getSingleton();
    }

    @Test
    void testLzeProjit() {
        Prostor prostor1 = new Prostor("hala", "xxxx", new HerniPlan());
        Prostor prostor2 = new Prostor("bufet", "yyyy", new HerniPlan());

        prostor1.setVychod(prostor2);
        prostor2.setVychod(prostor1);

        assertEquals(prostor2, prostor1.vratSousedniProstor("bufet"));
        assertEquals(null, prostor2.vratSousedniProstor("pokoj"));
    }

    @Test
    void testNavratoveHodnotyObjektuPriObjektuVec() {

        Vec neco = new Vec("neco", true, true);
        Prostor prostor = new Prostor("nazev", "popis", null);

        Object o = 20;

        assertEquals(((Object) neco).getClass().getName(), "logika.Vec");
        assertEquals(((Object) prostor).getClass().getName(), "logika.Prostor");
        assertEquals(o.getClass().getName(), "java.lang.Integer");
    }

    @Test
    void testNemoznostVejitDoZamceneMistnosti() {
        assertEquals(hra.zpracujPrikaz("jdi Stodola"), "Tam je zamčeno, tam se nedostaneš\n");
    }

    @Test
    void testNemoznostSebratNeprenositelnouVec() {
        assertEquals(hra.zpracujPrikaz("seber barel"), "barel je moc tezka, tu neuneses");

    }

    @Test
    void testNemoznostJitDoneviditelneMistnosti(){
        hra.zpracujPrikaz("jdi les");
        assertEquals(hra.zpracujPrikaz("jdi taboriste"), "Tam se odsud jít nedá!");
    }

    @Test
    void testZkusOdemknoutBezKlice() {
        assertEquals(hra.zpracujPrikaz("odemkni stodola"), "Hele, tak bud nemas klic, nebo tam odsud cesta nevede, takze se pakuj");
    }

    @Test
    void zkusitVymenuBezVeci() {
        hra.zpracujPrikaz("jdi les");
        hra.zpracujPrikaz("jdi vesnice");
        assertEquals(hra.zpracujPrikaz("vymen lahev"), "nemas potrebne veci pro tuto vymenu, bez hledat");

    }

    @Test
    void zkusitVymenuSVecma() {
        hra.zpracujPrikaz("jdi hospoda");
        hra.zpracujPrikaz("seber lahevalkoholu");
        hra.zpracujPrikaz("jdi mesto");
        hra.zpracujPrikaz("jdi les");
        hra.zpracujPrikaz("jdi vesnice");
        assertEquals(hra.zpracujPrikaz("vymen lahev"), """
                tak to byla naprosto silena vymena, jak myslis, dostals kamen a nuz, uzivej
                Počet životů 90/100
                Momentálně se nacházíš v prostoru: Vesnice
                Věci v místnosti: Kámen Klacek Barel
                Východy: Les Pustina PirátskáLoď
                Aktuální předměty v batohu: Nůž Kámen
                Kapacita batohu: 2/15""");
    }

    @Test
    void testZkusitVymenuKdeNicNeni() {
        assertEquals(hra.zpracujPrikaz("vymen lahev"), "hele tady neni moc co vymenovat");
    }

    @Test
    void testPouzitVecCoNejdePouzit() {
        hra.zpracujPrikaz("seber strepy");
        assertEquals(hra.zpracujPrikaz("pouzij strepy"), "bud to nemas v batohu nebo to nemuzes pouzit");
    }

    @Test
    void testProzkoumejANicNenajdi() {
        assertEquals(hra.zpracujPrikaz("prozkoumej mesto"), """
                V místnosti Město jsi nenašel nic
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: Město
                Věci v místnosti: MrtváKrysa Střepy Barel Lampa
                Východy: Hory Les HlubokýLes Hospoda Kostel
                Zamčené východy: DůmKováře Stodola
                Aktuální předměty v batohu: V batohu nemáš nic
                Kapacita batohu: 0/15""");
    }

    @Test
    void testPrikazVymenaBezVymeny() {
        assertEquals(hra.zpracujPrikaz("vymena"), "tady nic nevymenis kamo");
    }

    @Test
    void testPrikazVymenaSVymenouKorektniVypsani() {
        hra.zpracujPrikaz("jdi les");
        hra.zpracujPrikaz("jdi vesnice");
        assertEquals(hra.zpracujPrikaz("vymena"), "zvláštní kupec ti nabizi za LahevAlkoholu svůj nůž a kámen\n" +
                " pokud mas vsechny predmety tak to aktivujes napsanim vymen lahev");
    }
}
