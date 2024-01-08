package talde2.exe.submenus;

import java.io.FileNotFoundException;
import java.util.Scanner;

import talde2.exe.submenus.productsSubmenus.CsvMenu;
import talde2.exe.submenus.productsSubmenus.JsonMenu;
import talde2.exe.submenus.productsSubmenus.XmlMenu;

public class ProductsMenu {
    public static void run() throws FileNotFoundException {
        boolean csvSubMenuRunning = true;
        Scanner in = new Scanner(System.in);

        while (csvSubMenuRunning) {
            System.out.println();
            System.out.println("PRODUCTS");
            System.out.println("========================");
            System.out.println("1. Work with Csv files");
            System.out.println("2. Work with Xml files ");
            System.out.println("3. Work with Json files");
            System.out.println("4. Go back to main menu");
            System.out.println("What would you like to do? ");

            if (in.hasNextInt()) {
                int csvOption = in.nextInt();
                switch (csvOption) {
                    case 1:
                        CsvMenu.csvSubMenu();
                        break;
                    case 2:
                        XmlMenu.xmlSubMenu();
                        break;
                    case 3:
                        JsonMenu.jsonSubMenu();
                        break;
                    case 4:
                        csvSubMenuRunning = false;
                    default:
                        System.out.println("Invalid option. Please select a valid option.");
                        break;
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                in.nextLine(); // Consume the invalid input
            }
        }
        
    }
}
