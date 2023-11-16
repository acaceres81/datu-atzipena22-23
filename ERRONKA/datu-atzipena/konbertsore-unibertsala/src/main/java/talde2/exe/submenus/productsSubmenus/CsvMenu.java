package talde2.exe.submenus.productsSubmenus;

import java.io.FileNotFoundException;
import java.util.Scanner;

import talde2.model.products.filesaccess.CsvConverter;

public class CsvMenu {

    public static void csvSubMenu() throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        boolean csvSubMenuRunning = true;
        String fileName;

        while (csvSubMenuRunning) {
            System.out.println();
            System.out.println("CSV");
            System.out.println("========================");
            System.out.println("1. Export products.");
            System.out.println("2. Import products. ");
            System.out.println("3. Export one or more products by id.");
            System.out.println("4. Go back to Product menu");
            System.out.println("What would you like to do? ");

            int option = in.nextInt();
            switch (option) {
                case 1:
                    System.out.print("Enter the name you want for the new Csv file: ");
                    fileName = in.next();
                    in.nextLine();
                    
                    CsvConverter.exportAll(fileName);
                    break;
                case 2:
                    System.out.print("Enter the name of the Csv file you want to import: ");
                    fileName = in.next();
                    in.nextLine();
                    
                    CsvConverter.importAll(fileName);
                    break;
                case 3:
                    System.out.print("Enter the name you want for the new Csv file: ");
                    fileName = in.next();
                    in.nextLine();

                    System.out.print("Enter the number of products you want to export: ");
                    int count = in.nextInt();

                    CsvConverter.exportSome(fileName, count);
                    break;
                case 4:
                    csvSubMenuRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
                    break;
            }
        }
        in.close();
    }
}
