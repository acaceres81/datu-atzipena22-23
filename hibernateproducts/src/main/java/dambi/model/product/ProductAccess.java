package dambi.model.product;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import dambi.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
 
public class ProductAccess {

    public static void save(Product product) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(product);
            transaction.commit();
        }
    }

    public static Product getProductById(int id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.openSession()) {
            return session.get(Product.class, id);
        }
    }

    public static List<Product> getAllProducts() {
       SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
            criteria.from(Product.class);
            List<Product> data = session.createQuery(criteria).getResultList();
            return data;

        }
    }

    public static void updateProduct(Product product) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            product.setName(product.getName() + " aldatua.");
            // session.persist(product); //session berria danez, objektua ez dago hari
            // lotuta
            session.merge(product);// attach the object with a row of the database
            transaction.commit();
        }
    }

    public static void updateProduct(int id) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Product product = session.get(Product.class, id);// here product is a persistent object
            product.setPrice(product.getPrice() * 1.1);
            transaction.commit();// hemen, produktua datubasean eguneratuko da
        }
    }
    

    public static void deleteProduct(Product product) {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        try (Session session = factory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(product);//id hori daukan produktua borratzen dau. Egoera persistentean egon ez arren
            transaction.commit();
        }
    }

}
