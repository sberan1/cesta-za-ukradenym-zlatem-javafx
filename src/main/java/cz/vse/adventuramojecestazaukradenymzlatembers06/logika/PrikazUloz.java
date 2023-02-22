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



    public PrikazUloz(Hra hra) {
        this.hra = hra;
    }

    /**
     * @param parametry počet parametrů závisí na konkrétním příkazu.
     * @return
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
     * @return
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }

    /**
     * @return
     */
    @Override
    public int getCounter() {
        return counter;
    }

    private String prectiString() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("> ");
        return scanner.nextLine();
    }

    public Integer parseIntOrNull(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
