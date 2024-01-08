package talde2.model.products.dbaccess;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import talde2.model.products.entities.Product;
import talde2.model.products.entities.Products;

public class ReadWriteDatabase {
    public static Products readAll() {
        Products resultList = new Products();
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
            List<Product> results = session.createQuery("from Product", Product.class).list();

            // Iterating through the list of retrieved Product objects and printing their
            // string.
            for (Product product : results) {
                resultList.add(product);
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
    public static void write(Products products) {
        Configuration configuration = new Configuration().configure();

        // Attempting to create a session factory and open a session
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
                Session session = sessionFactory.openSession()) {

            // Beginning a new transaction
            Transaction transaction = session.beginTransaction();

            // Merging the product into the database
            for (Product product : products.getProducts()) {
                session.merge(product);
            }

            // Committing the transaction
            transaction.commit();

        } catch (Exception e) {
            System.out.println("\n\u001B[31mConversion could not be done\u001B[37m");
            System.exit(0);
        }
    }

    public static Products readOne(List<Integer> productsId) {
        Products resultList = new Products();
        Configuration configuration = new Configuration().configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
                Session session = sessionFactory.openSession()) {

            session.beginTransaction();

            List<Product> results = session.createQuery("from Product where id in :ids", Product.class)
                    .setParameter("ids", productsId)
                    .getResultList();

            for (Product product : results) {
                resultList.add(product);
            }
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("\n\u001B[31mConversion could not be done\u001B[37m");
            System.exit(0);
        }
        return resultList;
    }
}
