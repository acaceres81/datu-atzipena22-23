package talde2.model.products.filesaccess;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import talde2.model.products.dbaccess.ReadWriteDatabase;
import talde2.model.products.entities.Product;
import talde2.model.products.entities.Products;

public class XmlConverter {
    private String strFileOut;
    private static Scanner in = new Scanner(System.in);

    public XmlConverter(String strFileOut) {
        this.strFileOut = strFileOut;
    }

    // Method to write products into an XML file
    public int writeXml(Products products) {
        int productCount = 0;
        try {
            // Order products by ID
            List<Product> productList = products.getProducts();
            Collections.sort(productList, Comparator.comparingInt(Product::getId));

            // Init jaxb marshaller
            JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // Set this flag to true to format the output
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Marshaling of java objects into XML (output to file)
            jaxbMarshaller.marshal(products, new File(strFileOut));

            productCount = productList.size();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return productCount;
    }

    // Method to export products into an XML file
    public static void exportAll(String fileName) {
        try {

            XmlConverter xml = new XmlConverter(fileName + ".xml");

            Products products = ReadWriteDatabase.readAll(); // Reading products from the database
            int productCount = xml.writeXml(products); // Writing the products into the XML file

            if (productCount > 0) {
                System.out.println("\n\u001B[32mThe conversion has been made\u001B[37m\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\n\u001B[31mConversion could not be done\u001B[37m\n");
        }
    }

    // Method to import products from an XML file
    public static void importAll(String fileName) throws FileNotFoundException {
        try {

            File file = new File(fileName + ".xml");

            if (file.exists() && !file.isDirectory()) {
                JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

                // Add exception handling for unmarshalling
                try {
                    Products products = (Products) jaxbUnmarshaller.unmarshal(file);
                    ReadWriteDatabase.write(products); // Add the products to the database
                    System.out.println("\n\u001B[32mThe conversion has been made\u001B[37m\n");
                } catch (JAXBException e) {
                    e.printStackTrace();
                    System.out.println("Error during unmarshalling: " + e.getMessage());
                }
            } else {
                System.out.println("File does not exist or is a directory");
            }
        } catch (JAXBException e) {
            e.printStackTrace();
            System.out.println("\n\u001B[31mConversion could not be done\u001B[37m");
        }
    }

    // Method to export some products to a XML file
    public static void exportSome(String fileName, int count) throws FileNotFoundException {
        try {
            List<Integer> productsId = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                System.out.println("Enter the id of product " + (i + 1));
                int productId = in.nextInt();
                productsId.add(productId);
            }
            Products products = ReadWriteDatabase.readOne(productsId); // Call the readOne method

            XmlConverter xml = new XmlConverter(fileName + ".xml");

            xml.writeXml(products); // Writing the products into the XML file

            List<Product> productList = products.getProducts();
            Collections.sort(productList, Comparator.comparingInt(Product::getId));
            if (products != null) {
                System.out.println("\n\u001B[32mThe conversion has been made\u001B[37m\n");
            }

        } catch (Exception e) {
            System.out.println("\n\u001B[31mConversion could not be done\u001B[37m");
            System.out.println("\u001B[33mThere is no product with that id\u001B[37m\n");
            e.printStackTrace();
        }
    }
}
