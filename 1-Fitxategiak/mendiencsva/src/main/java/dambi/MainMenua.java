package dambi;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MainMenua {
    static BufferedReader inputStream = null;
    static String artxiboa = "Mendiak.csv";

    public static void main(String[] args) throws IOException {

        Scanner in = new Scanner(System.in);
        int aukera = 0;
        do {
            System.out.println("MENDIEN MENUA");
            System.out.println("==================");
            System.out.println("1. Mendien zerrenda ikusi (taula formatuan)");
            System.out.println("2. Mendirik altuena bistaratu");
            System.out.println("3. Mendiak esportatu (Araba.csv, Bizkaia.csv, Gipuzkoa.csv)");
            System.out.println("4. ...");
            System.out.println("5. Irten");
            System.out.println("");
            System.out.print("Aukeratu zenbaki bat: ");
            aukera = in.nextInt();
            switch (aukera) {
                case 1:
                    mendiZerrenda();
                    break;
                case 2:
                    mendiAltuena();
                    break;
                case 3:
                    mendiakEsportatu();
                    break;
                case 5:
                    System.out.println("Eskerrik asko programa hau erabiltzeagatik.");
                    break;
                default:
                    System.out.println("Aukera okerra. Saiatu berriz.");
            }
        } while (aukera != 5);
        in.next();
    }

    public static void mendiZerrenda() throws IOException {

        try {
            inputStream = new BufferedReader(new FileReader(artxiboa));

            String l;
            while ((l = inputStream.readLine()) != null) {
                String[] arrOfStr = l.split(";");

                for (String a : arrOfStr)
                    System.out.printf("%15s", a + "|");
                System.out.println();
            }
            System.out.println();

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public static void mendiAltuena() throws IOException {
        try {
            inputStream = new BufferedReader(new FileReader(artxiboa));
            int altuena = 0;
            String mendia = "";

            String l;
            while ((l = inputStream.readLine()) != null) {
                String[] arrOfStr = l.split(";");

                try{
                    if(Integer.parseInt(arrOfStr[1])>altuena){
                    altuena = Integer.parseInt(arrOfStr[1]);
                    mendia =arrOfStr[0];
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            System.out.println("Mendirik altuena " + mendia + " da " + altuena + " metroko atuerarekin.");

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public static void mendiakEsportatu() throws IOException {
        BufferedReader inputStream = null;
        PrintWriter araba = null;
        PrintWriter gipuzkoa = null;
        PrintWriter bizkaia = null;
        PrintWriter nafarroa = null;
    
        try {
            inputStream = new BufferedReader(new FileReader(artxiboa));
            araba = new PrintWriter(new FileWriter("Araba.csv"));
            bizkaia = new PrintWriter(new FileWriter("Bizkaia.csv"));
            gipuzkoa = new PrintWriter(new FileWriter("Gipuzkoa.csv"));
            nafarroa = new PrintWriter(new FileWriter("Nafarroa.csv"));

            String line;
            while ((line = inputStream.readLine()) != null) {
                if(line.contains("PROBINTZIA")){
                    araba.println(line);
                    bizkaia.println(line);
                    gipuzkoa.println(line);
                    nafarroa.println(line);
                }else if (line.contains("Araba")) {
                    araba.println(line);
                } else if (line.contains("Bizkaia")) {
                     bizkaia.println(line);
                } else if (line.contains("Gipuzkoa")) {
                     gipuzkoa.println(line);
                } else if (line.contains("Nafarroa")) {
                     nafarroa.println(line);
                }
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (araba != null) {
                araba.close();
            }
            if (bizkaia != null) {
                bizkaia.close();
            }
            if (gipuzkoa != null) {
                gipuzkoa.close();
            }
            if (nafarroa != null) {
                nafarroa.close();
            }
        }
    }

}
