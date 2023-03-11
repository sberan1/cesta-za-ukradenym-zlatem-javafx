/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package cz.vse.adventuramojecestazaukradenymzlatembers06.main;


import cz.vse.adventuramojecestazaukradenymzlatembers06.logika.*;
import cz.vse.adventuramojecestazaukradenymzlatembers06.uiText.TextoveRozhrani;

/*******************************************************************************
 * Třída  Start je hlavní třídou projektu,
 * který představuje jednoduchou textovou adventuru určenou k dalším úpravám a rozšiřování
 *
 * @author    Jarmila Pavlíčková
 * @version   ZS 2016/2017
 */
public class Start
{
    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args)
    {
        if (args.length == 0){
            Adventura.main(args);
        }
        else if (args[0].equals("-vypis")) {
            IHra hra = Hra.getSingleton();
            hra.getHerniPlan().setDlouhyVypis(true);
            TextoveRozhrani ui = new TextoveRozhrani(hra);
            ui.hraj();
        } else if (args[0].equals("-text")) {
            IHra hra = Hra.getSingleton();
            TextoveRozhrani ui = new TextoveRozhrani(hra);
            ui.hraj();
        } else {
            System.out.println("chyba");
        }
    }
}
