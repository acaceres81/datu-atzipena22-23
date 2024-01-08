package talde2.exe.submenus.productsSubmenus;

import java.io.FileNotFoundException;
import java.util.Scanner;

import talde2.model.products.filesaccess.XmlConverter;

public class XmlMenu {

    public static void xmlSubMenu() throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        boolean xmlSubMenuRunning = true;
        String fileName;
        while (xmlSubMenuRunning) {
            System.out.println();
            System.out.println("XML");
            System.out.println("========================");
            System.out.println("1. Export products.");
            System.out.println("2. Import products. ");
            System.out.println("3. Export one or more products by id.");
            System.out.println("4. Go back to Product menu");
            System.out.println("What would you like to do? ");

            int xmlOption = in.nextInt();
            switch (xmlOption) {
                case 1:
                    System.out.println("Enter the name you want for the XML file: ");
                    fileName = in.next();
                    in.nextLine();

                    XmlConverter.exportAll(fileName);
                    break;
                case 2:
                    System.out.println("Enter the name of the XML file you want to import: ");
                    fileName = in.next();
                    in.nextLine();

                    XmlConverter.importAll(fileName);
                    break;
                case 3:
                    System.out.print("Enter the name you want for the new Xml file: ");
                    fileName = in.next();
                    in.nextLine();

                    System.out.print("Enter the number of products you want to export: ");
                    int count = in.nextInt();

                    XmlConverter.exportSome(fileName, count);
                    break;
                case 4:
                    xmlSubMenuRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
                    break;
            }
        }
        in.close();
    }
}
