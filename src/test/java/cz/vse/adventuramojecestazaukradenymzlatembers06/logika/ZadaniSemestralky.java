package cz.vse.adventuramojecestazaukradenymzlatembers06.logika;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ZadaniSemestralky {
    
    private Hra hra = new Hra();
    
    @BeforeEach
    public void setUp(){
        hra = new Hra();
    }

    @AfterEach
    void tearDown() {
        hra = new Hra();
    }


    // zde bude probihat test Hry, ktery bude slouzit jako zadani semestralni prace
    @Test
    void testHry() {

        /**
         * Mapa generovana pomoci online nastroje - inkarnate.com
         *
         * Mapa se nachazi v adresari souboru ve slozce dalsi soubory - nazev souboru mapa.jpeg
         *
         * ve slozce se take nachazi textovy soubor popisujici co jde v jakych prostorech najit za vychody a predmety
         *
         */

        //Uvítání - zapnutí hry
        assertEquals(hra.vratUvitani(), """
                Vítej v adventuře, kde je tvým cílem dojít do zamčené shované místnosti v čarodějově věži kde čaroděj shovává všechno ukradené zlato.
                Čeká tě těžký průchod a budeš muset cestou posbírat několik předmětů.
                 Napište 'nápověda', pokud si nevíte rady, jak hrát dál.\s
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: Město
                Věci v místnosti: MrtváKrysa Střepy Barel Lampa
                Východy: Hory Les HlubokýLes Hospoda Kostel
                Zamčené východy: DůmKováře Stodola
                Aktuální předměty v batohu: V batohu nemáš nic
                Kapacita batohu: 0/15""");

        //1. krok jdi kostel
        assertEquals(hra.zpracujPrikaz("jdi kostel"),
                """
                        Kostel -  Místo, kde jsme blíže bohu a můžeme zde najít
                        Počet životů 100/100
                        Momentálně se nacházíš v prostoru: Kostel
                        Věci v místnosti: Róba Žezlo Lavice
                        Východy: Město
                        Aktuální předměty v batohu: V batohu nemáš nic
                        Kapacita batohu: 0/15""");

        //2. krok prozkoumej kostel
        assertEquals(hra.zpracujPrikaz("prozkoumej kostel"),
                """
                        V místnosti Kostel jsi našel Klíč LektvarŽivota
                        Počet životů 100/100
                        Momentálně se nacházíš v prostoru: Kostel
                        Věci v místnosti: Róba Žezlo Lavice Klíč LektvarŽivota
                        Východy: Město
                        Aktuální předměty v batohu: V batohu nemáš nic
                        Kapacita batohu: 0/15""");

        //3. krok seber klic
        assertEquals(hra.zpracujPrikaz("seber klic"), """
                Sebral jsi Klíč
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: Kostel
                Věci v místnosti: Róba Žezlo Lavice LektvarŽivota
                Východy: Město
                Aktuální předměty v batohu: Klíč
                Kapacita batohu: 1/15""");

        //4.krok seber lektvarzivota
        assertEquals(hra.zpracujPrikaz("seber lektvarzivota"), """
                Sebral jsi LektvarŽivota
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: Kostel
                Věci v místnosti: Róba Žezlo Lavice
                Východy: Město
                Aktuální předměty v batohu: Klíč LektvarŽivota
                Kapacita batohu: 2/15""");

        //5. krok jdi mesto
        assertEquals(hra.zpracujPrikaz("jdi mesto"), """
                Město - Tady se děje všechno svaté i nesvaté
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: Město
                Věci v místnosti: MrtváKrysa Střepy Barel Lampa
                Východy: Hory Les HlubokýLes Hospoda Kostel
                Zamčené východy: DůmKováře Stodola
                Aktuální předměty v batohu: Klíč LektvarŽivota
                Kapacita batohu: 2/15""");

        //6.krok odemkni dumkovare
        assertEquals(hra.zpracujPrikaz("odemkni dumkovare"), """
                Odemkl jsi DůmKováře
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: Město
                Věci v místnosti: MrtváKrysa Střepy Barel Lampa
                Východy: Hory Les HlubokýLes Hospoda Kostel DůmKováře
                Zamčené východy: Stodola
                Aktuální předměty v batohu: LektvarŽivota
                Kapacita batohu: 1/15""");

        //7.krok jdi dumkovare
        assertEquals(hra.zpracujPrikaz("jdi dumkovare"), """
                DůmKováře - tady bydlí kovář
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: DůmKováře
                Věci v místnosti: Meč Stůl Nůž
                Východy: Město
                Aktuální předměty v batohu: LektvarŽivota
                Kapacita batohu: 1/15""");

        //8.krok seber mec
        assertEquals(hra.zpracujPrikaz("seber mec"), """
                Sebral jsi Meč
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: DůmKováře
                Věci v místnosti: Stůl Nůž
                Východy: Město
                Aktuální předměty v batohu: LektvarŽivota Meč
                Kapacita batohu: 2/15""");

        //9.krok jdi mesto
        assertEquals(hra.zpracujPrikaz("jdi mesto"), """
                Město - Tady se děje všechno svaté i nesvaté
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: Město
                Věci v místnosti: MrtváKrysa Střepy Barel Lampa
                Východy: Hory Les HlubokýLes Hospoda Kostel DůmKováře
                Zamčené východy: Stodola
                Aktuální předměty v batohu: LektvarŽivota Meč
                Kapacita batohu: 2/15""");

        //10. krok jdi hospoda
        assertEquals(hra.zpracujPrikaz("jdi hospoda"), """
                Hospoda - tady se pije
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: Hospoda
                Věci v místnosti: Půllitr Nůž Stůl Židle LahevAlkoholu
                Východy: Město
                Aktuální předměty v batohu: LektvarŽivota Meč
                Kapacita batohu: 2/15""");

        //11.krok seber LahevAlkoholu
        assertEquals(hra.zpracujPrikaz("seber LahevAlkoholu"), """
                Sebral jsi LahevAlkoholu
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: Hospoda
                Věci v místnosti: Půllitr Nůž Stůl Židle
                Východy: Město
                Aktuální předměty v batohu: LektvarŽivota Meč LahevAlkoholu
                Kapacita batohu: 3/15""");

        //12.krok jdi mesto
        assertEquals(hra.zpracujPrikaz("jdi mesto"),
                """
                        Město - Tady se děje všechno svaté i nesvaté
                        Počet životů 100/100
                        Momentálně se nacházíš v prostoru: Město
                        Věci v místnosti: MrtváKrysa Střepy Barel Lampa
                        Východy: Hory Les HlubokýLes Hospoda Kostel DůmKováře
                        Zamčené východy: Stodola
                        Aktuální předměty v batohu: LektvarŽivota Meč LahevAlkoholu
                        Kapacita batohu: 3/15""");

        //13.krok jdi hlubokyles
        assertEquals(hra.zpracujPrikaz("jdi hlubokyLes"),
                """
                        HlubokýLes - Při vstupu do Hlubokého lesa tě napadli vlci a ubrali ti 20 životů než jsi je stihl zahnat.
                        Počet životů 80/100
                        Momentálně se nacházíš v prostoru: HlubokýLes
                        Věci v místnosti: Klacek Kámen Strom
                        Východy: Město Hory Táborák Pustina
                        Aktuální předměty v batohu: LektvarŽivota Meč LahevAlkoholu
                        Kapacita batohu: 3/15""");

        //14. krok prozkoumej hlubokyles
        assertEquals(hra.zpracujPrikaz("prozkoumej hlubokyles"), """
                V místnosti HlubokýLes jsi našel Klíč
                Počet životů 80/100
                Momentálně se nacházíš v prostoru: HlubokýLes
                Věci v místnosti: Klacek Kámen Strom Klíč
                Východy: Město Hory Táborák Pustina
                Aktuální předměty v batohu: LektvarŽivota Meč LahevAlkoholu
                Kapacita batohu: 3/15""");

        //15. krok seber klíč
        assertEquals(hra.zpracujPrikaz("seber klíč"), """
                Sebral jsi Klíč
                Počet životů 80/100
                Momentálně se nacházíš v prostoru: HlubokýLes
                Věci v místnosti: Klacek Kámen Strom
                Východy: Město Hory Táborák Pustina
                Aktuální předměty v batohu: LektvarŽivota Meč LahevAlkoholu Klíč
                Kapacita batohu: 4/15""");

        //16.krok jdi pustina
        assertEquals(hra.zpracujPrikaz("jdi pustina"), """
                Pustina - tady krom suché hlíny nic nenajdeš, cestou jsi z vyčerpání ztratil 10 životů.
                Počet životů 70/100
                Momentálně se nacházíš v prostoru: Pustina
                Věci v místnosti: Tady nic není
                Východy: HlubokýLes Les Vesnice
                Zamčené východy: Stodola
                Aktuální předměty v batohu: LektvarŽivota Meč LahevAlkoholu Klíč
                Kapacita batohu: 4/15""");
        //17. krok jdi vesnice
        assertEquals(hra.zpracujPrikaz("jdi vesnice"), """
                Vesnice - Tady se neděje absolutně nic zajímavého, potkal jsi pár ovcí, a zvláštního kupce co ti za LahevAlkoholu nabízí svůj nůž a kámen.
                Počet životů 70/100
                Momentálně se nacházíš v prostoru: Vesnice
                Věci v místnosti: Kámen Klacek Barel
                Východy: Les Pustina PirátskáLoď
                Aktuální předměty v batohu: LektvarŽivota Meč LahevAlkoholu Klíč
                Kapacita batohu: 4/15""");
        //jdi pirátskálod
        assertEquals(hra.zpracujPrikaz("jdi pirátskálod"), """
                PirátskáLoď - byl jsi napaden a obklíčen, piráti ti nabízejí výměnu lahveAlkoholu a meče za tvůj život.
                Počet životů 70/100
                Momentálně se nacházíš v prostoru: PirátskáLoď
                Věci v místnosti: Tady nic není
                Východy: Odsud jentak neutečeš.
                Aktuální předměty v batohu: LektvarŽivota Meč LahevAlkoholu Klíč
                Kapacita batohu: 4/15""");
        //vymen zivot
        assertEquals(hra.zpracujPrikaz("vymen zivot"), """
                Když tě piráti okrádali tak jsi jim začal utíkat do Vesnice, zvládl jsi jednomu z nich ukrást klíč, přišel jsi o věci co po tobě chtěli a byl jsi postřelen za 50 životů.
                Počet životů 20/100
                Momentálně se nacházíš v prostoru: Vesnice
                Věci v místnosti: Kámen Klacek Barel
                Východy: Les Pustina
                Aktuální předměty v batohu: LektvarŽivota Klíč Klíč
                Kapacita batohu: 3/15""");
        //jdi Pustina
        assertEquals(hra.zpracujPrikaz("jdi Pustina"), """
                Pustina - tady krom suché hlíny nic nenajdeš, cestou jsi z vyčerpání ztratil 10 životů.
                Počet životů 10/100
                Momentálně se nacházíš v prostoru: Pustina
                Věci v místnosti: Tady nic není
                Východy: HlubokýLes Les Vesnice
                Zamčené východy: ČarodějovaVěž
                Aktuální předměty v batohu: LektvarŽivota Klíč Klíč
                Kapacita batohu: 3/15""");

        //pouzij LektvarZivota
        assertEquals(hra.zpracujPrikaz("pouzij lektvarZivota"), """
                Použil jsi LektvarŽivota
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: Pustina
                Věci v místnosti: Tady nic není
                Východy: HlubokýLes Les Vesnice
                Zamčené východy: ČarodějovaVěž
                Aktuální předměty v batohu: Klíč Klíč
                Kapacita batohu: 2/15""");

        //odemkni CarodejovaVEZ
        assertEquals(hra.zpracujPrikaz("odemkni CarodejovaVEZ"), """
                Odemkl jsi ČarodějovaVěž
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: Pustina
                Věci v místnosti: Tady nic není
                Východy: HlubokýLes Les Vesnice ČarodějovaVěž
                Aktuální předměty v batohu: Klíč
                Kapacita batohu: 1/15""");

        //jdi CarodejovaVEZ
        assertEquals(hra.zpracujPrikaz("jdi CarodejovaVEZ"), """
                ČarodějovaVěž - Vešel jsi dovnitř, kde tě napadl čaroděj, po dlouhé bitvě jsi ho svými magickými schopnostmi překonal. Přišel jsi o 70 životů.
                Počet životů 30/100
                Momentálně se nacházíš v prostoru: ČarodějovaVěž
                Věci v místnosti: Tady nic není
                Východy: Pustina
                Aktuální předměty v batohu: Klíč
                Kapacita batohu: 1/15""");

        //prozkoumej CarodejovaVEZ
        assertEquals(hra.zpracujPrikaz("prozkoumej CarodejovaVEZ"), """
                V místnosti ČarodějovaVěž jsi našel TajnáPokladnice
                Počet životů 30/100
                Momentálně se nacházíš v prostoru: ČarodějovaVěž
                Věci v místnosti: Tady nic není
                Východy: Pustina
                Zamčené východy: TajnáPokladnice
                Aktuální předměty v batohu: Klíč
                Kapacita batohu: 1/15""");

        //odemkni TajnáPokladnice
        assertEquals(hra.zpracujPrikaz("odemkni TajnáPokladnice"), """             
                Odemkl jsi TajnáPokladnice
                Počet životů 30/100
                Momentálně se nacházíš v prostoru: ČarodějovaVěž
                Věci v místnosti: Tady nic není
                Východy: Pustina TajnáPokladnice
                Aktuální předměty v batohu: V batohu nemáš nic
                Kapacita batohu: 0/15""");

        //jdi TajnáPokladnice
        assertEquals(hra.zpracujPrikaz("jdi TajnáPokladnice"), """
                Vyhrál jsi
                Dohrál jsi tuto úžasnou hru, našel jsi ukradené zlato a je už jen na tobě, jestli si ho necháš, nebo ho půjdeš vrátit do města. Děkuji za zahrání!""");

    }

    @Test
    void testHrySVypisem() {

        hra.getHerniPlan().setDlouhyVypis(true);

        assertEquals(hra.vratUvitani(), """
                Vítej v adventuře, kde je tvým cílem dojít do zamčené shované místnosti v čarodějově věži kde čaroděj shovává všechno ukradené zlato.
                Čeká tě těžký průchod a budeš muset cestou posbírat několik předmětů.
                 Napište 'nápověda', pokud si nevíte rady, jak hrát dál.\s
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: Město
                Věci v místnosti: MrtváKrysa Střepy Barel Lampa
                Východy: Hory Les HlubokýLes Hospoda Kostel
                Zamčené východy: DůmKováře Stodola
                Aktuální předměty v batohu: V batohu nemáš nic
                Kapacita batohu: 0/15
                Zatim nepouzite prikazy: prozkoumej odemkni nápověda vymen pouzij poloz vymena jdi seber konec\s
                Zatim neprochazene prostory: Hory Stodola Kostel DůmKováře Hospoda HlubokýLes Pustina Vesnice PirátskáLoď ČarodějovaVěž TajnáPokladnice Les Táborák Stodola Tábořiště\s
                Zatim nevidene veci: Róba Žezlo Lavice Klíč LektvarŽivota Meč Stůl Nůž Půllitr Židle LahevAlkoholu Klacek Kámen Strom Klíč Stan Klíč MalýLektvarŽivota Náhrdelník Pařez Sláma Seno\s""");

        //1. krok jdi kostel
        assertEquals(hra.zpracujPrikaz("jdi kostel"),
                """
                        Kostel -  Místo, kde jsme blíže bohu a můžeme zde najít
                        Počet životů 100/100
                        Momentálně se nacházíš v prostoru: Kostel
                        Věci v místnosti: Róba Žezlo Lavice
                        Východy: Město
                        Aktuální předměty v batohu: V batohu nemáš nic
                        Kapacita batohu: 0/15
                        Zatim nepouzite prikazy: prozkoumej odemkni nápověda vymen pouzij poloz vymena seber konec\s
                        Zatim neprochazene prostory: Hory Stodola DůmKováře Hospoda HlubokýLes Pustina Vesnice PirátskáLoď ČarodějovaVěž TajnáPokladnice Les Táborák Stodola Tábořiště\s
                        Zatim nevidene veci: Klíč LektvarŽivota Meč Stůl Nůž Půllitr Židle LahevAlkoholu Klacek Kámen Strom Klíč Stan Klíč MalýLektvarŽivota Náhrdelník Pařez Sláma Seno\s""");

        //2. krok prozkoumej kostel
        assertEquals(hra.zpracujPrikaz("prozkoumej kostel"),
                """
                        V místnosti Kostel jsi našel Klíč LektvarŽivota
                        Počet životů 100/100
                        Momentálně se nacházíš v prostoru: Kostel
                        Věci v místnosti: Róba Žezlo Lavice Klíč LektvarŽivota
                        Východy: Město
                        Aktuální předměty v batohu: V batohu nemáš nic
                        Kapacita batohu: 0/15
                        Zatim nepouzite prikazy: odemkni nápověda vymen pouzij poloz vymena seber konec\s
                        Zatim neprochazene prostory: Hory Stodola DůmKováře Hospoda HlubokýLes Pustina Vesnice PirátskáLoď ČarodějovaVěž TajnáPokladnice Les Táborák Stodola Tábořiště\s
                        Zatim nevidene veci: Meč Stůl Nůž Půllitr Židle LahevAlkoholu Klacek Kámen Strom Klíč Stan Klíč MalýLektvarŽivota Náhrdelník Pařez Sláma Seno\s""");

        //3. krok seber klic
        assertEquals(hra.zpracujPrikaz("seber klic"), """
                Sebral jsi Klíč
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: Kostel
                Věci v místnosti: Róba Žezlo Lavice LektvarŽivota
                Východy: Město
                Aktuální předměty v batohu: Klíč
                Kapacita batohu: 1/15
                Zatim nepouzite prikazy: odemkni nápověda vymen pouzij poloz vymena konec\s
                Zatim neprochazene prostory: Hory Stodola DůmKováře Hospoda HlubokýLes Pustina Vesnice PirátskáLoď ČarodějovaVěž TajnáPokladnice Les Táborák Stodola Tábořiště\s
                Zatim nevidene veci: Meč Stůl Nůž Půllitr Židle LahevAlkoholu Klacek Kámen Strom Klíč Stan Klíč MalýLektvarŽivota Náhrdelník Pařez Sláma Seno\s""");

        //4.krok seber lektvarzivota
        assertEquals(hra.zpracujPrikaz("seber lektvarzivota"), """
                Sebral jsi LektvarŽivota
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: Kostel
                Věci v místnosti: Róba Žezlo Lavice
                Východy: Město
                Aktuální předměty v batohu: Klíč LektvarŽivota
                Kapacita batohu: 2/15
                Zatim nepouzite prikazy: odemkni nápověda vymen pouzij poloz vymena konec\s
                Zatim neprochazene prostory: Hory Stodola DůmKováře Hospoda HlubokýLes Pustina Vesnice PirátskáLoď ČarodějovaVěž TajnáPokladnice Les Táborák Stodola Tábořiště\s
                Zatim nevidene veci: Meč Stůl Nůž Půllitr Židle LahevAlkoholu Klacek Kámen Strom Klíč Stan Klíč MalýLektvarŽivota Náhrdelník Pařez Sláma Seno\s""");

        //5. krok jdi mesto
        assertEquals(hra.zpracujPrikaz("jdi mesto"), """
                Město - Tady se děje všechno svaté i nesvaté
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: Město
                Věci v místnosti: MrtváKrysa Střepy Barel Lampa
                Východy: Hory Les HlubokýLes Hospoda Kostel
                Zamčené východy: DůmKováře Stodola
                Aktuální předměty v batohu: Klíč LektvarŽivota
                Kapacita batohu: 2/15
                Zatim nepouzite prikazy: odemkni nápověda vymen pouzij poloz vymena konec\s
                Zatim neprochazene prostory: Hory Stodola DůmKováře Hospoda HlubokýLes Pustina Vesnice PirátskáLoď ČarodějovaVěž TajnáPokladnice Les Táborák Stodola Tábořiště\s
                Zatim nevidene veci: Meč Stůl Nůž Půllitr Židle LahevAlkoholu Klacek Kámen Strom Klíč Stan Klíč MalýLektvarŽivota Náhrdelník Pařez Sláma Seno\s""");

        //6.krok odemkni dumkovare
        assertEquals(hra.zpracujPrikaz("odemkni dumkovare"), """
                Odemkl jsi DůmKováře
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: Město
                Věci v místnosti: MrtváKrysa Střepy Barel Lampa
                Východy: Hory Les HlubokýLes Hospoda Kostel DůmKováře
                Zamčené východy: Stodola
                Aktuální předměty v batohu: LektvarŽivota
                Kapacita batohu: 1/15
                Zatim nepouzite prikazy: nápověda vymen pouzij poloz vymena konec\s
                Zatim neprochazene prostory: Hory Stodola DůmKováře Hospoda HlubokýLes Pustina Vesnice PirátskáLoď ČarodějovaVěž TajnáPokladnice Les Táborák Stodola Tábořiště\s
                Zatim nevidene veci: Meč Stůl Nůž Půllitr Židle LahevAlkoholu Klacek Kámen Strom Klíč Stan Klíč MalýLektvarŽivota Náhrdelník Pařez Sláma Seno\s""");

        //7.krok jdi dumkovare
        assertEquals(hra.zpracujPrikaz("jdi dumkovare"), """
                DůmKováře - tady bydlí kovář
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: DůmKováře
                Věci v místnosti: Meč Stůl Nůž
                Východy: Město
                Aktuální předměty v batohu: LektvarŽivota
                Kapacita batohu: 1/15
                Zatim nepouzite prikazy: nápověda vymen pouzij poloz vymena konec\s
                Zatim neprochazene prostory: Hory Stodola Hospoda HlubokýLes Pustina Vesnice PirátskáLoď ČarodějovaVěž TajnáPokladnice Les Táborák Stodola Tábořiště\s
                Zatim nevidene veci: Půllitr Židle LahevAlkoholu Klacek Kámen Strom Klíč Stan Klíč MalýLektvarŽivota Náhrdelník Pařez Sláma Seno\s""");

        //8.krok seber mec
        assertEquals(hra.zpracujPrikaz("seber mec"), """
                Sebral jsi Meč
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: DůmKováře
                Věci v místnosti: Stůl Nůž
                Východy: Město
                Aktuální předměty v batohu: LektvarŽivota Meč
                Kapacita batohu: 2/15
                Zatim nepouzite prikazy: nápověda vymen pouzij poloz vymena konec\s
                Zatim neprochazene prostory: Hory Stodola Hospoda HlubokýLes Pustina Vesnice PirátskáLoď ČarodějovaVěž TajnáPokladnice Les Táborák Stodola Tábořiště\s
                Zatim nevidene veci: Půllitr Židle LahevAlkoholu Klacek Kámen Strom Klíč Stan Klíč MalýLektvarŽivota Náhrdelník Pařez Sláma Seno\s""");

        //9.krok jdi mesto
        assertEquals(hra.zpracujPrikaz("jdi mesto"), """
                Město - Tady se děje všechno svaté i nesvaté
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: Město
                Věci v místnosti: MrtváKrysa Střepy Barel Lampa
                Východy: Hory Les HlubokýLes Hospoda Kostel DůmKováře
                Zamčené východy: Stodola
                Aktuální předměty v batohu: LektvarŽivota Meč
                Kapacita batohu: 2/15
                Zatim nepouzite prikazy: nápověda vymen pouzij poloz vymena konec\s
                Zatim neprochazene prostory: Hory Stodola Hospoda HlubokýLes Pustina Vesnice PirátskáLoď ČarodějovaVěž TajnáPokladnice Les Táborák Stodola Tábořiště\s
                Zatim nevidene veci: Půllitr Židle LahevAlkoholu Klacek Kámen Strom Klíč Stan Klíč MalýLektvarŽivota Náhrdelník Pařez Sláma Seno\s""");

        //10. krok jdi hospoda
        assertEquals(hra.zpracujPrikaz("jdi hospoda"), """
                Hospoda - tady se pije
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: Hospoda
                Věci v místnosti: Půllitr Nůž Stůl Židle LahevAlkoholu
                Východy: Město
                Aktuální předměty v batohu: LektvarŽivota Meč
                Kapacita batohu: 2/15
                Zatim nepouzite prikazy: nápověda vymen pouzij poloz vymena konec\s
                Zatim neprochazene prostory: Hory Stodola HlubokýLes Pustina Vesnice PirátskáLoď ČarodějovaVěž TajnáPokladnice Les Táborák Stodola Tábořiště\s
                Zatim nevidene veci: Klacek Kámen Strom Klíč Stan Klíč MalýLektvarŽivota Náhrdelník Pařez Sláma Seno\s""");

        //11.krok seber LahevAlkoholu
        assertEquals(hra.zpracujPrikaz("seber LahevAlkoholu"), """
                Sebral jsi LahevAlkoholu
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: Hospoda
                Věci v místnosti: Půllitr Nůž Stůl Židle
                Východy: Město
                Aktuální předměty v batohu: LektvarŽivota Meč LahevAlkoholu
                Kapacita batohu: 3/15
                Zatim nepouzite prikazy: nápověda vymen pouzij poloz vymena konec\s
                Zatim neprochazene prostory: Hory Stodola HlubokýLes Pustina Vesnice PirátskáLoď ČarodějovaVěž TajnáPokladnice Les Táborák Stodola Tábořiště\s
                Zatim nevidene veci: Klacek Kámen Strom Klíč Stan Klíč MalýLektvarŽivota Náhrdelník Pařez Sláma Seno\s""");

        //12.krok jdi mesto
        assertEquals(hra.zpracujPrikaz("jdi mesto"),
                """
                        Město - Tady se děje všechno svaté i nesvaté
                        Počet životů 100/100
                        Momentálně se nacházíš v prostoru: Město
                        Věci v místnosti: MrtváKrysa Střepy Barel Lampa
                        Východy: Hory Les HlubokýLes Hospoda Kostel DůmKováře
                        Zamčené východy: Stodola
                        Aktuální předměty v batohu: LektvarŽivota Meč LahevAlkoholu
                        Kapacita batohu: 3/15
                        Zatim nepouzite prikazy: nápověda vymen pouzij poloz vymena konec\s
                        Zatim neprochazene prostory: Hory Stodola HlubokýLes Pustina Vesnice PirátskáLoď ČarodějovaVěž TajnáPokladnice Les Táborák Stodola Tábořiště\s
                        Zatim nevidene veci: Klacek Kámen Strom Klíč Stan Klíč MalýLektvarŽivota Náhrdelník Pařez Sláma Seno\s""");

        //13.krok jdi hlubokyles
        assertEquals(hra.zpracujPrikaz("jdi hlubokyLes"),
                """
                        HlubokýLes - Při vstupu do Hlubokého lesa tě napadli vlci a ubrali ti 20 životů než jsi je stihl zahnat.
                        Počet životů 80/100
                        Momentálně se nacházíš v prostoru: HlubokýLes
                        Věci v místnosti: Klacek Kámen Strom
                        Východy: Město Hory Táborák Pustina
                        Aktuální předměty v batohu: LektvarŽivota Meč LahevAlkoholu
                        Kapacita batohu: 3/15
                        Zatim nepouzite prikazy: nápověda vymen pouzij poloz vymena konec\s
                        Zatim neprochazene prostory: Hory Stodola Pustina Vesnice PirátskáLoď ČarodějovaVěž TajnáPokladnice Les Táborák Stodola Tábořiště\s
                        Zatim nevidene veci: Klíč Stan Klíč MalýLektvarŽivota Náhrdelník Pařez Sláma Seno\s""");

        //14. krok prozkoumej hlubokyles
        assertEquals(hra.zpracujPrikaz("prozkoumej hlubokyles"), """
                V místnosti HlubokýLes jsi našel Klíč
                Počet životů 80/100
                Momentálně se nacházíš v prostoru: HlubokýLes
                Věci v místnosti: Klacek Kámen Strom Klíč
                Východy: Město Hory Táborák Pustina
                Aktuální předměty v batohu: LektvarŽivota Meč LahevAlkoholu
                Kapacita batohu: 3/15
                Zatim nepouzite prikazy: nápověda vymen pouzij poloz vymena konec\s
                Zatim neprochazene prostory: Hory Stodola Pustina Vesnice PirátskáLoď ČarodějovaVěž TajnáPokladnice Les Táborák Stodola Tábořiště\s
                Zatim nevidene veci: Stan Klíč MalýLektvarŽivota Náhrdelník Pařez Sláma Seno\s""");

        //15. krok seber klíč
        assertEquals(hra.zpracujPrikaz("seber klíč"), """
                Sebral jsi Klíč
                Počet životů 80/100
                Momentálně se nacházíš v prostoru: HlubokýLes
                Věci v místnosti: Klacek Kámen Strom
                Východy: Město Hory Táborák Pustina
                Aktuální předměty v batohu: LektvarŽivota Meč LahevAlkoholu Klíč
                Kapacita batohu: 4/15
                Zatim nepouzite prikazy: nápověda vymen pouzij poloz vymena konec\s
                Zatim neprochazene prostory: Hory Stodola Pustina Vesnice PirátskáLoď ČarodějovaVěž TajnáPokladnice Les Táborák Stodola Tábořiště\s
                Zatim nevidene veci: Stan Klíč MalýLektvarŽivota Náhrdelník Pařez Sláma Seno\s""");

        //16.krok jdi pustina
        assertEquals(hra.zpracujPrikaz("jdi pustina"), """
                Pustina - tady krom suché hlíny nic nenajdeš, cestou jsi z vyčerpání ztratil 10 životů.
                Počet životů 70/100
                Momentálně se nacházíš v prostoru: Pustina
                Věci v místnosti: Tady nic není
                Východy: HlubokýLes Les Vesnice
                Zamčené východy: Stodola
                Aktuální předměty v batohu: LektvarŽivota Meč LahevAlkoholu Klíč
                Kapacita batohu: 4/15
                Zatim nepouzite prikazy: nápověda vymen pouzij poloz vymena konec\s
                Zatim neprochazene prostory: Hory Stodola Vesnice PirátskáLoď ČarodějovaVěž TajnáPokladnice Les Táborák Stodola Tábořiště\s
                Zatim nevidene veci: Stan Klíč MalýLektvarŽivota Náhrdelník Pařez Sláma Seno\s""");
        //17. krok jdi vesnice
        assertEquals(hra.zpracujPrikaz("jdi vesnice"), """
                Vesnice - Tady se neděje absolutně nic zajímavého, potkal jsi pár ovcí, a zvláštního kupce co ti za LahevAlkoholu nabízí svůj nůž a kámen.
                Počet životů 70/100
                Momentálně se nacházíš v prostoru: Vesnice
                Věci v místnosti: Kámen Klacek Barel
                Východy: Les Pustina PirátskáLoď
                Aktuální předměty v batohu: LektvarŽivota Meč LahevAlkoholu Klíč
                Kapacita batohu: 4/15
                Zatim nepouzite prikazy: nápověda vymen pouzij poloz vymena konec\s
                Zatim neprochazene prostory: Hory Stodola PirátskáLoď ČarodějovaVěž TajnáPokladnice Les Táborák Stodola Tábořiště\s
                Zatim nevidene veci: Stan Klíč MalýLektvarŽivota Náhrdelník Pařez Sláma Seno\s""");
        //jdi pirátskálod
        assertEquals(hra.zpracujPrikaz("jdi pirátskálod"), """
                PirátskáLoď - byl jsi napaden a obklíčen, piráti ti nabízejí výměnu lahveAlkoholu a meče za tvůj život.
                Počet životů 70/100
                Momentálně se nacházíš v prostoru: PirátskáLoď
                Věci v místnosti: Tady nic není
                Východy: Odsud jentak neutečeš.
                Aktuální předměty v batohu: LektvarŽivota Meč LahevAlkoholu Klíč
                Kapacita batohu: 4/15
                Zatim nepouzite prikazy: nápověda vymen pouzij poloz vymena konec\s
                Zatim neprochazene prostory: Hory Stodola ČarodějovaVěž TajnáPokladnice Les Táborák Stodola Tábořiště\s
                Zatim nevidene veci: Stan Klíč MalýLektvarŽivota Náhrdelník Pařez Sláma Seno\s""");
        //vymen zivot
        assertEquals(hra.zpracujPrikaz("vymen zivot"), """
                Když tě piráti okrádali tak jsi jim začal utíkat do Vesnice, zvládl jsi jednomu z nich ukrást klíč, přišel jsi o věci co po tobě chtěli a byl jsi postřelen za 50 životů.
                Počet životů 20/100
                Momentálně se nacházíš v prostoru: Vesnice
                Věci v místnosti: Kámen Klacek Barel
                Východy: Les Pustina
                Aktuální předměty v batohu: LektvarŽivota Klíč Klíč
                Kapacita batohu: 3/15
                Zatim nepouzite prikazy: nápověda pouzij poloz vymena konec\s
                Zatim neprochazene prostory: Hory Stodola ČarodějovaVěž TajnáPokladnice Les Táborák Stodola Tábořiště\s
                Zatim nevidene veci: Stan Klíč MalýLektvarŽivota Náhrdelník Pařez Sláma Seno\s""");
        //jdi Pustina
        assertEquals(hra.zpracujPrikaz("jdi Pustina"), """
                Pustina - tady krom suché hlíny nic nenajdeš, cestou jsi z vyčerpání ztratil 10 životů.
                Počet životů 10/100
                Momentálně se nacházíš v prostoru: Pustina
                Věci v místnosti: Tady nic není
                Východy: HlubokýLes Les Vesnice
                Zamčené východy: ČarodějovaVěž
                Aktuální předměty v batohu: LektvarŽivota Klíč Klíč
                Kapacita batohu: 3/15
                Zatim nepouzite prikazy: nápověda pouzij poloz vymena konec\s
                Zatim neprochazene prostory: Hory Stodola ČarodějovaVěž TajnáPokladnice Les Táborák Stodola Tábořiště\s
                Zatim nevidene veci: Stan Klíč MalýLektvarŽivota Náhrdelník Pařez Sláma Seno\s""");

        //pouzij LektvarZivota
        assertEquals(hra.zpracujPrikaz("pouzij lektvarZivota"), """
                Použil jsi LektvarŽivota
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: Pustina
                Věci v místnosti: Tady nic není
                Východy: HlubokýLes Les Vesnice
                Zamčené východy: ČarodějovaVěž
                Aktuální předměty v batohu: Klíč Klíč
                Kapacita batohu: 2/15
                Zatim nepouzite prikazy: nápověda poloz vymena konec\s
                Zatim neprochazene prostory: Hory Stodola ČarodějovaVěž TajnáPokladnice Les Táborák Stodola Tábořiště\s
                Zatim nevidene veci: Stan Klíč MalýLektvarŽivota Náhrdelník Pařez Sláma Seno\s""");

        //odemkni CarodejovaVEZ
        assertEquals(hra.zpracujPrikaz("odemkni CarodejovaVEZ"), """
                Odemkl jsi ČarodějovaVěž
                Počet životů 100/100
                Momentálně se nacházíš v prostoru: Pustina
                Věci v místnosti: Tady nic není
                Východy: HlubokýLes Les Vesnice ČarodějovaVěž
                Aktuální předměty v batohu: Klíč
                Kapacita batohu: 1/15
                Zatim nepouzite prikazy: nápověda poloz vymena konec\s
                Zatim neprochazene prostory: Hory Stodola ČarodějovaVěž TajnáPokladnice Les Táborák Stodola Tábořiště\s
                Zatim nevidene veci: Stan Klíč MalýLektvarŽivota Náhrdelník Pařez Sláma Seno\s""");

        //jdi CarodejovaVEZ
        assertEquals(hra.zpracujPrikaz("jdi CarodejovaVEZ"), """
                ČarodějovaVěž - Vešel jsi dovnitř, kde tě napadl čaroděj, po dlouhé bitvě jsi ho svými magickými schopnostmi překonal. Přišel jsi o 70 životů.
                Počet životů 30/100
                Momentálně se nacházíš v prostoru: ČarodějovaVěž
                Věci v místnosti: Tady nic není
                Východy: Pustina
                Aktuální předměty v batohu: Klíč
                Kapacita batohu: 1/15
                Zatim nepouzite prikazy: nápověda poloz vymena konec\s
                Zatim neprochazene prostory: Hory Stodola TajnáPokladnice Les Táborák Stodola Tábořiště\s
                Zatim nevidene veci: Stan Klíč MalýLektvarŽivota Náhrdelník Pařez Sláma Seno\s""");

        //prozkoumej CarodejovaVEZ
        assertEquals(hra.zpracujPrikaz("prozkoumej CarodejovaVEZ"), """
                V místnosti ČarodějovaVěž jsi našel TajnáPokladnice
                Počet životů 30/100
                Momentálně se nacházíš v prostoru: ČarodějovaVěž
                Věci v místnosti: Tady nic není
                Východy: Pustina
                Zamčené východy: TajnáPokladnice
                Aktuální předměty v batohu: Klíč
                Kapacita batohu: 1/15
                Zatim nepouzite prikazy: nápověda poloz vymena konec\s
                Zatim neprochazene prostory: Hory Stodola TajnáPokladnice Les Táborák Stodola Tábořiště\s
                Zatim nevidene veci: Stan Klíč MalýLektvarŽivota Náhrdelník Pařez Sláma Seno\s""");

        //odemkni TajnáPokladnice
        assertEquals(hra.zpracujPrikaz("odemkni TajnáPokladnice"), """             
                Odemkl jsi TajnáPokladnice
                Počet životů 30/100
                Momentálně se nacházíš v prostoru: ČarodějovaVěž
                Věci v místnosti: Tady nic není
                Východy: Pustina TajnáPokladnice
                Aktuální předměty v batohu: V batohu nemáš nic
                Kapacita batohu: 0/15
                Zatim nepouzite prikazy: nápověda poloz vymena konec\s
                Zatim neprochazene prostory: Hory Stodola TajnáPokladnice Les Táborák Stodola Tábořiště\s
                Zatim nevidene veci: Stan Klíč MalýLektvarŽivota Náhrdelník Pařez Sláma Seno\s""");

        //jdi TajnáPokladnice
        assertEquals(hra.zpracujPrikaz("jdi TajnáPokladnice"), """
                Vyhrál jsi
                Dohrál jsi tuto úžasnou hru, našel jsi ukradené zlato a je už jen na tobě, jestli si ho necháš, nebo ho půjdeš vrátit do města. Děkuji za zahrání!""");

    }
}
