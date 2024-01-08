/* package talde2.model.converters;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

import talde2.model.products.entities.Product;
import talde2.model.products.entities.Products;
import talde2.model.products.entities.Stock;

public class Csv {
    Scanner in = new Scanner(System.in);
    static BufferedReader inputStream = null;
    Products products = new Products();

    public Products csvToProducts() throws NumberFormatException, IOException {

        String fileNameCsv;
        boolean fileFound = false;

        do {
            System.out.print("Enter the name of the Csv file you want to convert (include the extension): ");
            fileNameCsv = in.nextLine();

            try {
                inputStream = new BufferedReader(new FileReader("./" + fileNameCsv));
                String l;

                while ((l = inputStream.readLine()) != null) {
                    // Split each line on the ; and add it to an Array of strings
                    String[] arrOfStrings = l.split(";");
                    // skip the first line because it's the header.
                    if (arrOfStrings[0].equals("ID")) {
                        continue;
                    } else {
                        // if stock equals null we change it to 0 to avoid exception
                        // NumberFormatException
                        if (arrOfStrings[3].equals("null")) {
                            arrOfStrings[3] = "0";
                        }
                        Stock stock = new Stock(new BigDecimal(arrOfStrings[3]));
                        Product product = new Product(Integer.parseInt(arrOfStrings[0]), arrOfStrings[1],
                                arrOfStrings[2],
                                stock, Float.parseFloat(arrOfStrings[4]));
                        products.add(product);
                    }
                }
                fileFound = true;
            } catch (FileNotFoundException e) {
                System.out.println(
                        "\n\u001B[31mTHE FILE YOU WANT TO CONVERT COULD NOT BE REACHED, DID YOU WRITE THE EXTENSION?\u001B[37m\n");
            } catch (NullPointerException e) {
                System.out.println("\n\u001B[33mThe file was not created because no products were found\u001B[37m\n");
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        } while (!fileFound);

        return products;
    }

    public void productToCsv(Products products) throws IOException {
        //prompt user to enter the csv file where the products will be saved.
        System.out.println("Enter the name of the file you want to save (include .csv the extension): ");
        String fileSave = in.nextLine();

        File csvFile = new File(fileSave);
        BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));

        // Write the header of the file
        writer.write("ID;Name;Description;Stock;Price\n");

        // wirte a line for each product into the .csv file
        for (int i = 0; i < products.getProducts().size(); i++) {
            Product product = products.getProducts().get(i);
            // put US format to avoid , as a decimal separator
            String csvLine = String.format(Locale.US, "%d;%s;%s;%s;%.2f\n",
                    product.getIdentity(),
                    product.getIzena(),
                    product.getDeskripzioa(),
                    product.getStock(),
                    product.getPrezioa());
            writer.write(csvLine);
        }

        writer.close();

    }
} */
