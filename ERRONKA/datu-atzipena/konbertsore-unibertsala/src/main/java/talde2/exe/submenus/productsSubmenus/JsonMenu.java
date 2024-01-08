package talde2.exe.submenus.productsSubmenus;

import java.io.FileNotFoundException;
import java.util.Scanner;

import talde2.model.products.filesaccess.JsonConverter;

public class JsonMenu {
    public static void jsonSubMenu() throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        boolean jsonSubMenuRunning = true;
        String fileName;

        while (jsonSubMenuRunning) {
            System.out.println();
            System.out.println("JSON");
            System.out.println("========================");
            System.out.println("1. Export products.");
            System.out.println("2. Import products. ");
            System.out.println("3. Export one or more products by id.");
            System.out.println("4. Go back to Product menu");
            System.out.println("What would you like to do? ");

            int jsonOption = in.nextInt();
            switch (jsonOption) {
                
                case 1:
                    System.out.println("Enter the name you want for the Json file: ");
                    fileName = in.next();
                    in.nextLine();

                    JsonConverter.exportAll(fileName);
                    break;
                case 2:
                    System.out.println("Enter the name of the Json file you want to import: ");
                    fileName = in.next();
                    in.nextLine();
                    JsonConverter.importAll(fileName);
                    break;
                case 3:
                    System.out.print("Enter the name you want for the new Json file: ");
                    fileName = in.next();
                    in.nextLine();

                    System.out.print("Enter the number of products you want to export: ");
                    int count = in.nextInt();

                    JsonConverter.exportSome(fileName, count);
                    break;
                case 4:
                    jsonSubMenuRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
                    break;
            }

        }
        in.close();
    }   
}
