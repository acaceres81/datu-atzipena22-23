package talde2.model.products.filesaccess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

import talde2.model.products.dbaccess.ReadWriteDatabase;
import talde2.model.products.entities.Product;
import talde2.model.products.entities.Products;
import talde2.model.products.entities.Stock;

public class CsvConverter {
    public String strFileOut;
    public static Scanner in = new Scanner(System.in);

    public CsvConverter(String fileOut) throws FileNotFoundException {
        this.strFileOut = fileOut;
    }

    // Method to write products into a CSV file
    public int writeCsv(Products products) {
        int productCount = 0;

        // Order by id
        List<Product> productList = products.getProducts();
        Collections.sort(productList, Comparator.comparingInt(Product::getId));

        try (PrintWriter outputStream = new PrintWriter(new FileWriter(strFileOut))) {
            // Writing the products into the CSV file
            outputStream.println("ID; NAME; DESCRIPTION; STOCK; PRICE;");
            for (Product product : products.getProducts()) {
                if (product.getStock() != null && product.getStock().getDate() != null
                        && product.getDeskripzioa() != null) {
                    productCount++;
                    try {
                        JSONObject izenaJson = new JSONObject(product.getIzena());
                        String izenak = izenaJson.getString("en_US");

                        JSONObject deskripzioaJson = new JSONObject(product.getDeskripzioa());
                        String deskripzioak = deskripzioaJson.getString("en_US");

                        // Removing HTML tags
                        deskripzioak = deskripzioak.replaceAll("<p>", "").replaceAll("<br>", "").replaceAll("</p>", "");

                        // Writing each product's details into the CSV file
                        outputStream
                                .println(product.getId() + ";" + izenak + ";" + deskripzioak + ";" + product.getStock()
                                        + ";" + product.getPrezioa());
                    } catch (JSONException e) {
                        System.out.println("Error occurred while parsing JSON: " + e.getMessage());
                        // Handle the exception according to your application's requirements
                    }
                } else if (product.getStock() == null) {
                    productCount++;
                    try {
                        JSONObject izenaJson = new JSONObject(product.getIzena());
                        String izenak = izenaJson.getString("en_US");

                        JSONObject deskripzioaJson = new JSONObject(product.getDeskripzioa());
                        String deskripzioak = deskripzioaJson.getString("en_US");

                        // Removing HTML tags
                        deskripzioak = deskripzioak.replaceAll("<p>", "").replaceAll("<br>", "").replaceAll("</p>", "");

                        // Writing each product's details into the CSV file
                        outputStream.println(product.getId() + ";" + izenak + ";" + deskripzioak + ";" + 0
                                + ";" + product.getPrezioa());
                    } catch (JSONException e) {
                        System.out.println("Error occurred while parsing JSON: " + e.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("\n\u001B[31mConversion could not be done\u001B[37m\n");
        }
        return productCount;
    }

    // Method to export products to a CSV file
    public static void exportAll(String fileName) throws FileNotFoundException {
        try {
            Products products = new Products();

            CsvConverter csv = new CsvConverter(fileName + ".csv");

            products = ReadWriteDatabase.readAll(); // Reading products from the database
            csv.writeCsv(products); // Writing the products into the CSV file

            if (products != null) {
                System.out.println("\n\u001B[32mThe conversion has been made\u001B[37m\n");
            }
        } catch (Exception e) {
            System.out.println("\n\u001B[31mConversion could not be done\u001B[37m\n");
        }
    }

    // Method to import products from a CSV file
    public static void importAll(String fileName) throws FileNotFoundException {
        try {
            Products products = new Products();

            File file = new File(fileName + ".csv"); // Creating a File object with the provided file name
            Scanner scanner = new Scanner(file);

            if (scanner.hasNextLine()) { // Skipping the header
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] values = data.split(";");

                // Creating a new Product object
                if (values.length == 5) {
                    Product product = new Product();
                    Stock stock = new Stock();
                    JSONObject izenaJson = new JSONObject();
                    izenaJson.put("en_US", values[1]).put("es_ES", values[1]);

                    JSONObject deskripzioaJson = new JSONObject();
                    deskripzioaJson.put("en_US", "<p>" + values[2] + "</p>").put("es_ES", "<p>" + values[2] + "</p>");

                    // Setting the fields of the Product object based on the data in the CSV
                    product.setId(Integer.parseInt(values[0]));
                    product.setIzena(izenaJson.toString());
                    product.setDeskripzioa(deskripzioaJson.toString());
                    product.setPrezioa(Double.parseDouble(values[4]));

                    // Create a new stock object
                    stock.setQuantity(new BigDecimal(values[3])); // Set the quantity

                    // Set the stock in the product
                    product.setStock(stock);

                    // Add the product to the list of products
                    products.add(product);
                }
            }
            // Calling the write method to write the product into the database
            ReadWriteDatabase.write(products);
            System.out.println("\n\u001B[32mThe conversion has been made\u001B[37m\n");
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("\n\u001B[31mConversion could not be done\u001B[37m");
            System.out.println("\u001B[33mThere is no file with that name\u001B[37m\n");
        }
    }

    // Method to export some products to a CSV file
    public static void exportSome(String fileName, int count) throws FileNotFoundException {
        try {
            List<Integer> productsId = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                System.out.println("Enter the id of product " + (i + 1));
                int productId = in.nextInt();
                productsId.add(productId);
            }
            Products products = ReadWriteDatabase.readOne(productsId); // Call the readOne method

            CsvConverter csv = new CsvConverter(fileName + ".csv");

            csv.writeCsv(products); // Writing the products into the CSV file

            List<Product> productList = products.getProducts();
            Collections.sort(productList, Comparator.comparingInt(Product::getId));
            if (products != null) {
                System.out.println("\n\u001B[32mThe conversion has been made\u001B[37m\n");
            }

        } catch (Exception e) {
            System.out.println("\n\u001B[31mConversion could not be done\u001B[37m");
            System.out.println("\u001B[33mThere is no product with that id\u001B[37m\n");
        }
    }
}
