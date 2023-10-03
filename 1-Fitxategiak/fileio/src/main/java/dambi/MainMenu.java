package dambi;


import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class MainMenu {
    public static String FITXATEGIA = "Mendiak.csv";

    /**This method is the main menu of the program */
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int aukera = 0;
        do {
            System.out.println();
            System.out.println("MENUAREN IZENBURUA");
            System.out.println("====================================");
            System.out.println("1.- Fitxategi/Direktoria existitzen den egiaztatu.");
            System.out.println("2.- Direktorio baten edukia erakutsi.");
            System.out.println("3.- Direktorio egitura sortu.");
            System.out.println("4.- Fitxategia sortu.");
            System.out.println("5.- Irten");
            System.out.println("");
            System.out.print("Aukeratu zenbaki bat: ");
            aukera = scanner.nextInt();
            switch (aukera) {
                case 1:
                    pathAbsolutuaBilatu();
                    break;
                case 2:
                    edukiaIkusi();
                    break;
                case 3:
                    direktorioakSortu();
                    break;
                case 4:
                    fitxategiaSortu();
                    break;
                case 5:
                    System.out.println("Eskerrik asko programa hau erabiltzeagatik.");
                    break;
                default:
                    System.out.println("Aukera okerra. Saiatu berriz.");
            }
        } while (aukera != 5);
    }

    /** Erabiltzaileari fitxategi/direktorio baten path absolutoa eskatu eta gure fitxategi sisteman ba ote daukagun egiaztatu behar du funtzio honek */
    public static void pathAbsolutuaBilatu() throws Exception{
        Scanner scanner = new Scanner(System.in);

        System.out.print("Sartu fitxategi/direktorioaren path absolutoa: ");
        String path = scanner.next();

        Path ruta = Paths.get(path);

        // Egiaztatu path-a existitzen dela
        try {
            Path fp = ruta.toRealPath();
            System.out.println("Sartutako path-a badugu");
        } catch (NoSuchFileException x) {
            // Path-a ez da existitzen
            System.err.format("Sartutako path-a ez dugu");
            System.out.println();
        } catch (Exception x) {
            // Beste motatako errorea
            System.err.format("%s%n", x);
            System.out.println();
        }

        // Scanner objektua itxi
        //scanner.close(); Ixterakoan metodoaren barruan, errorea sortzen da.
    }

    /** Direktorio zehatz baten lehen mailako edukia erakutsi behar du funtzio honek */
    public static void edukiaIkusi(){
        // Scanner objektua sortu
        Scanner scanner = new Scanner(System.in);

        // Karpeta baten path absolutoa eskatu erabiltzaileari
        System.out.print("Sartu karpetaren path absolutoa: ");
        String karpetaPath = scanner.next();

        // File objektua sortu karpetaPath erabiliz
        File karpeta = new File(karpetaPath);

        // Egiaztatu karpeta existitzen dela
        if (karpeta.exists() && karpeta.isDirectory()) {
            // Karpeta existitzen da, egiaztatu fitxategiak daudela
            File[] fitxategiak = karpeta.listFiles();

            if (fitxategiak != null && fitxategiak.length > 0) {
                System.out.println(karpetaPath + " karpetaren edukia:");
                for (File fitxategia : fitxategiak) {
                    if (fitxategia.isFile()) {
                        System.out.println("|- " + fitxategia.getName());
                    } else if (fitxategia.isDirectory()) {
                        System.out.println("|- " + fitxategia.getName() + "/");
                    }
                }
            } else {
                System.out.println("Karpeta hutsik dago.");
            }
        } else {
            System.out.println("Karpeta hori ez da aurkitzen sisteman.");
        }

        // Scanner objektua itxi
        //scanner.close(); Ixterakoan metodoaren barruan, errorea sortzen da.
    }


    public static void direktorioakSortu(){
        // Direktorioen path-ak definitu
        Path relative = Paths.get("1-Fitxategiak\\fileio");

        Path animaliak = Paths.get(relative + "\\animaliak");
        Path elikagaiak = Paths.get(relative + "\\elikagaiak");
        Path barazkiak = Paths.get(relative + "\\barazkiak");
        Path esnekiak = Paths.get(relative + "\\esnekiak");

        Path arrainak = Paths.get(relative + "\\animaliak\\arrainak");
        Path ugaztunak = Paths.get(relative + "\\animaliak\\ugaztunak");


        try {
            // Direktorioak sortu
            Files.createDirectory(animaliak);
            Files.createDirectory(elikagaiak);
            Files.createDirectory(barazkiak);
            Files.createDirectory(esnekiak);

            Files.createDirectory(arrainak);
            Files.createDirectory(ugaztunak);

            System.out.println("Direktorioak ondo sortu dira!");

        } catch (FileAlreadyExistsException x) {
            // Direktorioa jada sortuta badago
            System.err.format("Ezin izan da direktorioa sortu, jada sortuta dagoelako");
            System.out.println();
        } catch (IOException x) {
            // Beste motatako errorea
            System.err.format("createFile error: %s%n", x);
            System.out.println();
        }        
    }

    /** Galdera batzuk egin ondoren, fitxategi bat sortu behar du leku egokian */
    public static void fitxategiaSortu(){
        // Scanner objektua sortu
        Scanner scanner = new Scanner(System.in);

        // Karpetaren izena eskatu path-a sortzeko
        System.out.print("Zer zoaz deskribatzera? ");
        String karpeta = scanner.next();

        Path relative = Paths.get("1-Fitxategiak\\fileio");
        Path karpetaPath = Paths.get("");

        // Path-a ezarri
        switch (karpeta.toLowerCase()) {
            case "arrainak":
                // Arrainak
                karpetaPath = Paths.get(relative +  "\\animaliak\\arrainak");
                break;

            case "ugaztunak":
                // Ugaztunak
                karpetaPath = Paths.get(relative +  "\\animaliak\\ugaztunak");
                break;

            case "elikagaiak":
                // Elikagaiak
                karpetaPath = Paths.get(relative +  "\\elikagaiak");
                break;

            case "barazkiak":
                // Barazkiak
                karpetaPath = Paths.get(relative +  "\\barazkiak");
                break;

            case "esnekiak":
                // Esnekiak
                karpetaPath = Paths.get(relative +  "\\esnekiak");
                break;

            default:
                System.out.println("Sartutako aukera ez da egokia (Arrainak, Ugaztunak, Elikagaiak, Barazkiak, Esnekiak)...");
                break;
        }

        // Fitxategiaren izena eskatu erabiltzaileari
        System.out.print("Zein? ");
        String izena = scanner.next();
        izena.concat(".txt");

        // Fitxategiaren path-a definitu eta fitxategia sortu
        Path file = Paths.get(karpetaPath + "\\" + izena);
        try {
            Files.createFile(file);
        } catch (IOException x) {
            // Beste motatako errorea
            System.err.format("createFile error: %s%n", x);
            System.out.println();
        }

        // Fitxategiaren edukia eskatu erabiltzaileari
        System.out.print("Nolakoa da? ");
        String edukia = scanner.next();

        // Edukia idatze fitxategian
        Charset charset = Charset.forName("US-ASCII");
        String s = edukia;
        try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
            writer.write(s, 0, s.length());
        } catch (IOException x) {
            // Beste motatako errorea
            System.err.format("createFile error: %s%n", x);
            System.out.println();
        }

        // Scanner objektua itxi
        //scanner.close(); Ixterakoan metodoaren barruan, errorea sortzen da.
    }
}
