package talde2.model.employees.fileaccessEmployees;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import talde2.model.employees.dbaccessemployees.Department;
import talde2.model.employees.dbaccessemployees.Departments;
import talde2.model.employees.dbaccessemployees.Employee;
import talde2.model.employees.dbaccessemployees.Employees;

public class ReadWriteDatabaseEmployees {

    public static Employees read() {
        Employees resultList = new Employees();
        // Creating a configuration object and loading the configuration from default
        // configuration files.
        Configuration configuration = new Configuration().configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
                // Opening a session from the session factory.
                Session session = sessionFactory.openSession()) {

            // Beginning a transaction for the current session.
            session.beginTransaction();

            // Querying all instances of Product from the database and storing the results
            // in a list.
            // After that the list will have objects of Product type.
            List<Employee> results = session.createQuery("from Employee", Employee.class).list();

            // Iterating through the list of retrieved Product objects and printing their
            // string.
            for (Employee employee : results) {
                resultList.add(employee);
            }

            // Committing to make the changes persistent in the database.
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("\n\u001B[31mConversion could not be done\u001B[37m");
            System.exit(0);
        }
        return resultList;
    }

    // Method to write products into the database
    public static void write(Departments departments) {
        Configuration configuration = new Configuration().configure();

        // Attempting to create a session factory and open a session
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
                Session session = sessionFactory.openSession()) {

            // Beginning a new transaction
            Transaction transaction = session.beginTransaction();

            // Iterating over each product and merging it into the database
            
            for (Department department : departments.getDepartments()){
                session.merge(department);
            }
            // Committing the transaction
            transaction.commit();

        } catch (Exception e) {
            // Printing the stack trace in case of any exceptions
            System.out.println("\n\u001B[31mConversion could not be done\u001B[37m");
            System.exit(0);
        }
    }

    public static Employees reaByDep(String title) {
        Employees resultList = new Employees();
        // Creating a configuration object and loading the configuration from default
        // configuration files.
        Configuration configuration = new Configuration().configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
            // Opening a session from the session factory.
            Session session = sessionFactory.openSession()) {

            // Beginning a transaction for the current session.
            session.beginTransaction();

            // Querying all instances of Product from the database and storing the results
            // in a list.
            // After that the list will have objects of Product type.
            List<Employee> results = session.createQuery("from Employee where title = :job_title" , Employee.class).setParameter("job_title", title).list();
            
            // Iterating through the list of retrieved Product objects and printing their
            // string.
            for (Employee employee : results) {
                resultList.add(employee);
            }

            // Committing to make the changes persistent in the database.
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("\n\u001B[31mConversion could not be done\u001B[37m");
            System.exit(0);
        }
        return resultList;
    }
}
