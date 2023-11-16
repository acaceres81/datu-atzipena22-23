package talde2.model.employees.fileaccessEmployees;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import talde2.model.employees.dbaccessemployees.Department;
import talde2.model.employees.dbaccessemployees.Departments;
import talde2.model.employees.dbaccessemployees.Employee;
import talde2.model.employees.dbaccessemployees.Employees;


public class CsvConverterEmployees {
    public String strFileOut;


    public CsvConverterEmployees(String fileOut) throws FileNotFoundException {
        this.strFileOut = fileOut;
    }

    // Method to write employees into a CSV file
    public int write(Employees employees) throws FileNotFoundException {
        int employeeCount = 0;

        //List<Employee> employeeList = employees.getEmployees();

        try (PrintWriter outputStream = new PrintWriter(new FileWriter(strFileOut)))  {
            // Writing the employees into the CSV file
            outputStream.println("ID; NAME; TITLE;PHONE;EMAIL;DEPARTMENT");
            for (Employee employee : employees.getEmployees()) {
                employeeCount++;
                outputStream.println(employee.getEmpId() + ";" + employee.getName() + ";"
                + employee.getTitle() + ";" + employee.getPhone() + ";" + employee.getEmail() + ";" + employee.getDepartment());
                    
                
            }
        } catch (Exception e) {
            System.out.println("\n\u001B[31mConversion could not be done\u001B[37m\n");
            e.printStackTrace();
        }
        return employeeCount;
    }   

    // Method to export Employees to a CSV file
    public static void export(String fileName) throws FileNotFoundException {
        try {
            Employees employees = new Employees();

            CsvConverterEmployees csv = new CsvConverterEmployees(fileName + ".csv");

            employees = ReadWriteDatabaseEmployees.read(); // Reading employees from the database
            csv.write(employees); // Writing the employees into the CSV file

            if (employees != null) {
                System.out.println("\n\u001B[32mThe conversion has been made\u001B[37m\n");
            }
        } catch (Exception e) {
            System.out.println("\n\u001B[31mConversion could not be done\u001B[37m\n");
            e.printStackTrace();
        }
    }

    public static void imp(String fileName) {
        try {
            Departments departments = new Departments();

            File file = new File(fileName + ".csv"); // Creating a File object with the provided file name
            System.out.println(file);
            Scanner scanner = new Scanner(file); // Creating a scanner object to read from the CSV file

            if (scanner.hasNextLine()) { // Skipping the header
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) { // Reading and processing data from the CSV file
                String data = scanner.nextLine();
                String[] values = data.split(";");

                if(values.length == 2){
                    Department departmnet = new Department();
                    
                    departmnet.setId(Integer.parseInt(values[0]));
                    departmnet.setDepartment(values[1]);

                    departments.add(departmnet);
                    
                    // Writing the department into the database
                    ReadWriteDatabaseEmployees.write(departments);

                    System.out.println("\n\u001B[32mThe conversion has been made\u001B[37m\n");
                    
                }else{
                    System.out.println("the file you entered is not valid");
                    break;
                }
               
            }
            scanner.close();  

        } catch (FileNotFoundException e) {
            System.out.println("\n\u001B[31mConversion could not be done\u001B[37m");
            System.out.println("\u001B[33mThere is no file with that name\u001B[37m\n");
        }
    }

    public static void exportByTitle(String fileName, String title) throws FileNotFoundException {
        
        try {
            Employees employees = new Employees();
            
            CsvConverterEmployees csv = new CsvConverterEmployees(fileName + ".csv");
                        
            employees = ReadWriteDatabaseEmployees.reaByDep(title); // Reading employees from the database
            
            List<Employee> employeeList = employees.getEmployees();
            Collections.sort(employeeList, Comparator.comparingInt(Employee::getEmpId));

            csv.write(employees); // Writing the employees into the CSV file

            if (employees != null) {
                System.out.println("\n\u001B[32mThe conversion has been made\u001B[37m\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("\n\u001B[31mConversion could not be done\u001B[37m\n");
            e.printStackTrace();
        } catch (NullPointerException e){
            System.out.println("\n\u001B[31mConversion could not be done, that department does not exist\u001B[37m\n");
    
        }
    }
    
}
