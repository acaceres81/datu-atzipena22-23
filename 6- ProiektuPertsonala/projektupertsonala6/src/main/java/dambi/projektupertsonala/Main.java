package dambi.projektupertsonala;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;

import dambi.projektupertsonala.conversor.CsvToSector;

public class Main { 

    public static void main(String[] args) {
        displayMenu();
    }

    private static void displayMenu() {
        Scanner in = new Scanner(System.in);
        int aukera = 0;

        do {
            System.out.println("Menu nagusia:");
            System.out.println("1. Spring Boot Aplikazioa martxan jarri");
            System.out.println("2. Csv fitxategia Json fitxategira exportatu");
            System.out.println("3. irten");

            System.out.print("Zer egin nahi duzu? ");
            int choice = in.nextInt();

            switch (choice) {
                case 1:
                    runSpringBootApplication();
                    break;
                case 2:
                    runConversor();
                    break;
                case 3:
                    System.out.println("Eskerrik asko programa hau erabiltzeagatik.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }while (aukera != 3);
        in.next();
    }

    private static void runSpringBootApplication() {
        System.out.println("Running Spring Boot Application...");

        SpringApplication.run(ProjektupertsonalaApplication.class);
    }

    private static void runConversor() {
        CsvToSector.main(new String[0]);
    }

}

