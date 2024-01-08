// package talde2.model.converters;

// import java.io.FileNotFoundException;
// import java.io.FileOutputStream;
// import java.io.FileReader;
// import java.io.IOException;
// import java.math.BigDecimal;
// import java.util.Scanner;

// import javax.json.Json;
// import javax.json.JsonArray;
// import javax.json.JsonArrayBuilder;
// import javax.json.JsonObject;
// import javax.json.JsonReader;
// import javax.json.JsonWriter;

// import talde2.model.products.entities.Product;
// import talde2.model.products.entities.Products;
// import talde2.model.products.entities.Stock;

// public class JsonFile {
//     String fileNameCsv;
//     boolean fileFound = false;
//     Scanner in = new Scanner(System.in);

//     public Products JsonToProducts() throws IOException {
//         Products products = new Products();

//         boolean fileFound = false;
//         do {
//             try {
//                 // prompt the user to enter the json file they want to convert
//                 System.out.print("Enter the name of the JSON file you want to convert (include the extension): ");
//                 String fileName = in.nextLine();

//                 // Read the jason file and add it to a JsonArray
//                 JsonReader jsonReader = Json.createReader(new FileReader("./" + fileName));
//                 JsonArray jsonArray = jsonReader.readArray();

//                 // create the Product objects from the data in the JsonArray
//                 for (int i = 0; i < jsonArray.size(); i++) {
//                     JsonObject jsonObject = jsonArray.getJsonObject(i);
//                     Product product = new Product(
//                             jsonObject.getInt("ID"),
//                             jsonObject.getString("Izena"),
//                             jsonObject.getString("Deskribapena"),
//                             new Stock(new BigDecimal(jsonObject.getInt("stock"))),
//                             (float) jsonObject.getJsonNumber("prezioa").doubleValue());
//                     // add the product objects to the products object
//                     products.add(product);
//                 }
//                 fileFound = true;
//             } catch (FileNotFoundException e) {
//                 System.out.println(
//                         "\n\u001B[31mTHE FILE YOU WANT TO CONVERT COULD NOT BE REACHED, DID YOU WRITE THE EXTENSION?\u001B[37m\n");
//             } catch (NullPointerException e) {
//                 System.out.println("\n\u001B[33mThe file was not created becuase no products were found\u001B[37m\n");
//             }
//         } while (!fileFound);

//         return products;
//     }

//     public void productToJson(Products products) throws FileNotFoundException {
//         // prompt user to enter the csv file where the products will be saved.
//         System.out.println("Enter the name of the file you want to save (include .json the extension): ");
//         String fileSave = in.nextLine();
        
//         // declare the jasonArraybuilder and add the products
//         JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
//         for (int i = 0; i < products.getProducts().size(); i++) {
//             JsonObject productObject = Json.createObjectBuilder()
//                     .add("ID", products.getProducts().get(i).getIdentity())
//                     .add("Izena", products.getProducts().get(i).getIzena())
//                     .add("Deskribapena", products.getProducts().get(i).getDeskripzioa())
//                     .add("stock", products.getProducts().get(i).getStock().getQuantity())
//                     .add("prezioa", products.getProducts().get(i).getPrezioa())
//                     // .add("taldea", products.getProducts().get(i).getTaldea())
//                     .build();
//             jsonArrayBuilder.add(productObject);
//         }

//         //add the products from the jsonArrayBuilder to a json Array
//         JsonArray jsonArray = jsonArrayBuilder.build();

//         // writhe the json array into the json file the user input.
//         try (JsonWriter jsonWriter = Json.createWriter(new FileOutputStream("./" + fileSave))) {
//             jsonWriter.writeArray(jsonArray);
//         }
//     }
// }
