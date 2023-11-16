package talde2.exe.submenus;

import java.io.FileNotFoundException;
import java.util.Scanner;

import talde2.model.employees.fileaccessEmployees.CsvConverterEmployees;

public class EmployeesMenu {
    public static void run() throws FileNotFoundException {
        boolean subMenuRunning = true;
        Scanner in = new Scanner(System.in);
        String fileName;

        while (subMenuRunning) {
            System.out.println();
            System.out.println("HUMAN RESOURCES");
            System.out.println("========================");
            System.out.println("1. Export employees to a csv file.");
            System.out.println("2. Create a new department. ");
            System.out.println("3. Export employees per department to a csv file.");
            System.out.println("4. Go back to Employee menu");
            System.out.println("What would you like to do? ");

            if (in.hasNextInt()) {
                int csvOption = in.nextInt();
                switch (csvOption) {
                    case 1:
                        System.out.println("Enter the name you want for the new Csv file: ");
                        fileName = in.next();
                        in.nextLine();
                        CsvConverterEmployees.export(fileName);
                        break;
                    case 2:
                        System.out.println("Enter the name you want for the new Csv file: ");
                        fileName  = in.next();
                        in.nextLine();
                        CsvConverterEmployees.imp(fileName);
                        break;
                    case 3:
                        System.out.println("Enter the name you want for the new Csv file: ");
                        fileName  = in.next();
                        in.nextLine();
                        System.out.println("Enter the name of the title you want to check: ");
                        String title = in.next();
                        in.nextLine();
                        CsvConverterEmployees.exportByTitle(fileName,title);
                        break;
                    case 4:
                        subMenuRunning = false;
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
