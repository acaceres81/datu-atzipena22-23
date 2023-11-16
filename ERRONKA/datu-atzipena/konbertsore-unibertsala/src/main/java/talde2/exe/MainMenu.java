package talde2.exe;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import jakarta.xml.bind.JAXBException;
import talde2.exe.submenus.EmployeesMenu;
import talde2.exe.submenus.ProductsMenu;

public class MainMenu {
    public static void main(String[] args) throws IOException, JAXBException {
        int execute = 1;
        int option = 0;
        Scanner in = new Scanner(System.in);

        while (execute == 1) {
            try {
                System.out.println("");
                System.out.println("UNIVERSAL FILE CONVERSOR");
                System.out.println("========================");
                System.out.println("1. Work with products");
                System.out.println("2. Work with employees");
                System.out.println("3. Open odoo");
                System.out.println("4. Exit");

                boolean correct = false;

                do {
                    System.out.print("What would you like to do? ");
                    if (in.hasNextInt()) {
                        option = in.nextInt();
                        correct = true;
                    } else {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        in.nextLine(); // Consume the entire invalid input
                    }

                } while (!correct);

                switch (option) {
                    case 1:
                        ProductsMenu.run();
                        break;
                    case 2:
                        EmployeesMenu.run();
                        break;
                    case 3:
                        try {
                            // Define the command to run WebguneBatIreki
                            String javaCommand = "java";
                            String odooIreki = "WebguneBatIreki";

                            // Create a ProcessBuilder to run WebguneBatIreki
                            ProcessBuilder pb = new ProcessBuilder(javaCommand, odooIreki);
                            pb.directory(new File("../../zerbitzu-programazioa"));

                            // Start WebguneBatIreki as a subprocess
                            Process process = pb.start();

                            // Wait for WebguneBatIreki to complete
                            int exitCode = process.waitFor();
                            System.out.println("WebguneBatIreki exited with code " + exitCode);
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 4:
                        System.out.println("\u001B[33mThanks for using the converter, come again soon!\u001B[37m");
                        execute = 0; // Exit the loop
                        break;
                    default:
                        System.out.println("\u001B[33mThe number you entered is not in the options.\u001B[37m\n");
                        break;
                }
            } catch (InputMismatchException ex) {
                System.out.println("\u001B[33mYou need to enter a number.\u001B[37m\n");
                in.nextLine(); // Consume the invalid input
            }
        }
        in.close();
    }
}
