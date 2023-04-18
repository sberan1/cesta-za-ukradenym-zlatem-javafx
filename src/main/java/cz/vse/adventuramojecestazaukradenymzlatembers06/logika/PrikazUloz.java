package cz.vse.adventuramojecestazaukradenymzlatembers06.logika;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrikazUloz implements IPrikaz{

    private int counter = 0; //pocita pouziti prikazu
    private static final String NAZEV = "ulož"; //nazev prikazu a jeho zneni pro pouziti
    private Hra hra;
    File currentDirectory = new File("."); // current directory
    boolean viditelnost = true;


    /**
     * konstruktor
     * @param hra - hra, ktera se ma ulozit
     */
    public PrikazUloz(Hra hra) {
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
        System.out.println("Kam chceš soubor uložit?");


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
        System.out.println(++i + ". zaloz novy soubor");
        System.out.println("nebo napis konec pro ukonceni prikazu");
        String radek = prectiString();
        if (parseIntOrNull(radek) != null){
            if (Integer.parseInt(radek) <= rightFiles.size()){
                hra.ulozHru(rightFiles.get(Integer.parseInt(radek)-1).getName());
                return "uspesne ulozeno";
            }
            if (Integer.parseInt(radek) == rightFiles.size() + 1){
                System.out.println("napis jmeno souboru: ");
                String jmenoSouboru = prectiString();
                try {
                File myObj = new File(jmenoSouboru + ".txtsave");
                if (myObj.createNewFile()) {
                    hra.ulozHru(jmenoSouboru + ".txtsave");
                    return "Soubor vytvoren a hra ulozena";
                }
                    return "to nepude";
                }
                catch (IOException ignored){

                }
            }
            return "neplatne cislo, zkus ulozit znovu";
        }
        if (radek.equals("konec")){
            return "ukoncil jsi prikaz " + NAZEV;
        }
        return "musis napsat cislo souboru kam to chces ulozit";
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
     * vrati cislo pokud je zadano cislo, jinak null
     * @param value - String ktery chceme prevest na cislo
     * @return Integer nebo null podle toho jestli se to povedlo
     */

    public Integer parseIntOrNull(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
