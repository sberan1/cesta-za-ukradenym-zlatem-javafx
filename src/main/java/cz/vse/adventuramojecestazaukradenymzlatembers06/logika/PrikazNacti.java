package cz.vse.adventuramojecestazaukradenymzlatembers06.logika;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrikazNacti implements IPrikaz{

    private int counter = 0; //pocita pouziti prikazu
    private static final String NAZEV = "načti"; //nazev prikazu a jeho zneni pro pouziti
    private Hra hra;
    File currentDirectory = new File("."); // current directory
    boolean viditelnost = true;


    public PrikazNacti(Hra hra) {
        this.hra = hra;
    }

    /**
     * provede prikaz a zpeta se na cestu kam ulozit soubor
     *
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return zpráva, kterou vypíše hra hráči
     */
    @Override
    public String provedPrikaz(String... parametry) {
        counter++;
        System.out.println("odkud chceš hru načíst?");
        File[] files = currentDirectory.listFiles();
        List<File> rightFiles = new ArrayList<>();
        int i = 0;
        assert files != null;
        for (File file : files) {
            if (file.getName().endsWith(".txtsave")) {
                System.out.println(++i + ". " + file.getName());
                rightFiles.add(file);
            }
        }
        System.out.println("nebo napis konec pro ukonceni prikazu");

        String radek = prectiString();
        if (parseIntOrNull(radek) != null){
            if (Integer.parseInt(radek) <= rightFiles.size()){
                hra.nactiHru(rightFiles.get(Integer.parseInt(radek)-1).getName());
                return "uspesne nahrano";
            }
            return "neplatne cislo souboru, zkus nacist znovu";
        }
        if (radek.equals("konec")){
            return "ukoncil jsi prikaz " + NAZEV;
        }
        return "musis napsat cislo souboru odkud chces hru nacist";
    }

    /**
     * metoda pro ziskani nazvu prikazu
     * @return - nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

    /**
     * metoda pro ziskani poctu pouziti prikazu
     * @return - pocet pouziti prikazu
     */
    @Override
    public int getCounter() {
        return counter;
    }

    @Override
    public boolean isViditelny() {
        return viditelnost;
    }

    /**
     * metoda pro nacitani stringu z konzole
     * @return - zadany String
     */
    private String prectiString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextLine();
    }

    /**
     * Vraci ciselnou hodnotu s poctem pouziti prikazu, pouzivano pro statistiky a nove vypisy
     * @param value - String ktery chceme prevest na Integer
     * @return - Integer pokud se to podari, jinak null
     */

    public Integer parseIntOrNull(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
