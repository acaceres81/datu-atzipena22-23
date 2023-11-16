package talde2.model.products.entities;

import org.hibernate.annotations.ColumnTransformer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_template")
public class Product {
    @Id // Defining the primary key.
    @Column(name = "id") // Select the column of the database.
    private int id;

    // Make a join with the table product_template. The relation is 1:1, therefore
    // the conection is OneToOne.
    @OneToOne
    // In order to make the connection correctly, we have to reference the column we
    // want to join, i.e. we need to specify the column in stock_quant and the
    // column in the product_template where the join is made.
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Stock stock;

    // Column definition is used to especify the data type of the database.
    @Column(name = "name", columnDefinition = "jsonb")
    // Before inserting data into the database, it will be transformed to jsonb.
    @ColumnTransformer(write = "?::jsonb")
    private String izena;

    @Column(name = "description", columnDefinition = "jsonb")
    @ColumnTransformer(write = "?::jsonb")
    private String deskripzioa;

    @Column(name = "list_price")
    private double prezioa;

    @Column(name = "categ_id")
    private int categ_id = 8;

    @Column(name = "uom_id")
    private int uom_id = 1;

    @Column(name = "uom_po_id")
    private int uom_po_id = 1;

    public Product() {
    }

    public Product(int id, String izena, String deskripzioa, Stock stock, double prezioa) {
        this.id = id;
        this.izena = izena;
        this.deskripzioa = deskripzioa;
        this.stock = stock;
        this.prezioa = prezioa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIzena() {
        return izena;
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public String getDeskripzioa() {
        return deskripzioa;
    }

    public void setDeskripzioa(String deskripzioa) {
        this.deskripzioa = deskripzioa;
    }

    public double getPrezioa() {
        return prezioa;
    }

    public void setPrezioa(double prezioa) {
        this.prezioa = prezioa;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return " ID: " + id + "\n "
                + "Name: " + izena + "\n "
                + "Description: " + deskripzioa + "\n "
                + "Stock: " + stock + "\n "
                + "Price: " + prezioa + "\n ";
    }
}
