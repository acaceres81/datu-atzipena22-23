package talde2.model.products.entities;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Products") // Maps a class or an enum type to an XML element.
public class Products {
    List<Product> products = new ArrayList<>(); // Initialize the list

    public List<Product> getProducts() {
        return products;
    }

    @XmlElement(name = "Product") // Maps a property to a XML element derived from property name.
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    // Method to add a product to the list
    public void add(Product product) {
        this.products.add(product); // Simply add the product to the list
    }

    // Method to get a product by its ID
    public Product getById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null; // If the product with the specified ID is not found
    }

    // Overriding the toString() method to represent Products as a string
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Product p : this.products) {
            str.append(p.toString());
        }
        return str.toString();
    }

    public Stock getStock() {
        return null;
    }
}
