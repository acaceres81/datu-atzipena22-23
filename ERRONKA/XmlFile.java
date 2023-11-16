// package talde2.model.converters;

// import java.io.File;
// import java.io.FileNotFoundException;
// import java.util.Scanner;
// import jakarta.xml.bind.JAXBContext;
// import jakarta.xml.bind.JAXBException;
// import jakarta.xml.bind.Marshaller;
// import jakarta.xml.bind.Unmarshaller;
// import talde2.model.products.entities.Products;

// public class XmlFile {
    
//     Scanner in = new Scanner(System.in);
//     boolean fileFound = false;

//     public Products xmlToProducts() throws JAXBException, FileNotFoundException {
//         Products products = new Products();
//         do {
//             try {
//                 // prompt user to write the name of the file they want to convert
//                 System.out.print("Enter the name of the xml file you want to convert (include the extension): ");
//                 String fileName = in.next();
                
//                 // take the products from the .xml file and create products object
//                 File xmlFile = new File("./" + fileName);
//                 JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);
//                 Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//                 products = (Products) jaxbUnmarshaller.unmarshal(xmlFile);

//                 fileFound = true;
//             } catch (JAXBException e) {
//                 e.printStackTrace();
//             } catch (NullPointerException e) {
//                 System.out.println("\n\u001B[33mThe file was not created becuase no products were found\u001B[37m\n");
//             }
//         } while (!fileFound);
//         return products;
//     }

//     public void productsToXml(Products products) throws JAXBException{
//         //prompt user to enter the csv file where the products will be saved.
//         System.out.println("Enter the name of the file you want to save (include .xml the extension): ");
//         String fileSave = in.nextLine();

//         JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);
//         Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//         jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//         jaxbMarshaller.marshal(products, new File("./" + fileSave));
//         jaxbMarshaller.marshal(products, System.out);
//     }

// }
