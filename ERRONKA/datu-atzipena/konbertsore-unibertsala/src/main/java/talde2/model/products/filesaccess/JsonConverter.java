package talde2.model.products.filesaccess;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

import com.fasterxml.jackson.databind.ObjectMapper;

import talde2.model.products.dbaccess.ReadWriteDatabase;
import talde2.model.products.entities.Product;
import talde2.model.products.entities.Products;

public class JsonConverter {
    public String strFileOut;
    public static Scanner in = new Scanner(System.in);

    public JsonConverter(String fileOut) {
        this.strFileOut = fileOut;
    }

    // Method to write Products to a JSON file
    public int writeJson(Products products) {
        int productCount = 0;

        // Order products by ID
        List<Product> productList = products.getProducts();
        Collections.sort(productList, Comparator.comparingInt(Product::getId));

        try (JsonWriter jsonWriter = Json.createWriter(new FileOutputStream(strFileOut))) {
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder(); // Create the JsonArrayBuilder

            for (Product product : productList) {
                BigDecimal quantity;

                if (product.getStock() != null && product.getStock().getQuantity() != null) {
                    quantity = product.getStock().getQuantity(); // Assign the quantity if not null
                } else {
                    quantity = BigDecimal.ZERO; // Assign zero if any of the conditions are not met
                }

                // Create JSON object for each product
                JsonObjectBuilder productBuilder = Json.createObjectBuilder()
                        .add("id", product.getId())
                        .add("stock", quantity)
                        .add("prezioa", product.getPrezioa());

                // Check and add izena if not null
                if (product.getIzena() != null) {
                    productBuilder.add("izena", product.getIzena());
                }

                // Check and add deskripzioa if not null
                if (product.getDeskripzioa() != null) {
                    productBuilder.add("deskripzioa", product.getDeskripzioa());
                }

                // Build the product JSON object
                JsonObject productObject = productBuilder.build();

                // Add the product JSON object to the array
                arrayBuilder.add(productObject);
                productCount++;
            }

            JsonArray model = arrayBuilder.build(); // Build the final JSON array
            jsonWriter.writeArray(model); // Write the entire array to the JSON file

        } catch (Exception e) {
            e.printStackTrace();
        }
        return productCount;
    }

    // Method to export products to a JSON file
    public static void exportAll(String fileName) {
        try {
            Products products = new Products();

            JsonConverter jsonConverter = new JsonConverter(fileName + ".json");

            products = ReadWriteDatabase.readAll(); // Read data from database
            jsonConverter.writeJson(products); // Write data to JSON file

            if (products != null) {
                System.out.println("\n\u001B[32mThe conversion has been made\u001B[37m\n");
            }
        } catch (Exception e) {
            System.out.println("\n\u001B[31mConversion could not be done\u001B[37m\n");
        }
    }

    // Method to import products from a JSON file
    public static void importAll(String fileName) {
        try {
            Products products = new Products();

            ObjectMapper objectMapper = new ObjectMapper(); // ObjectMapper provides functionality for reading and
                                                            // writing JSON, either to and from basic POJOs

            File file = new File(fileName + ".json");

            // Read data from JSON file and map it to Products class
            Product product = objectMapper.readValue(file, Product.class);

            // Add the product to the products list
            products.add(product);

            // Add the products to the database
            ReadWriteDatabase.write(products);

            System.out.println("\n\u001B[32mThe conversion has been made\u001B[37m\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to export some products to a JSON file
    public static void exportSome(String fileName, int count) throws FileNotFoundException {
        try {
            List<Integer> productsId = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                System.out.println("Enter the id of product " + (i + 1));
                int productId = in.nextInt();
                productsId.add(productId);
            }
            Products products = ReadWriteDatabase.readOne(productsId); // Call the readOne method

            JsonConverter json = new JsonConverter(fileName + ".json");

            json.writeJson(products); // Writing the products into the JSON file

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
