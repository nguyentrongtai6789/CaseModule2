package model;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Product implements Serializable, Comparable<Product> {
    private static final long serialUID = 2343234L;
    private int id;
    private static int idProduct = 1;
    private String name;
    private String manufacturer;
    private double price;
    private int quantity;
    private String description;
    private Category category;

    public Product(String name, String manufacturer, double price, int quantity, String description, Category category) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.id = idProduct;
        this.category = category;
        idProduct++;
    }

    public Product() {
        this.id = idProduct;
        idProduct++;
    }

    public int getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static void setIdProduct(int idProduct) {
        Product.idProduct = idProduct;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("###,###.#");
        String formattedPrice = decimalFormat.format(price);
        return String.format("%-15d %-20s %-17s %-15s %-23d %-13s %-10s",
                id, name, manufacturer, formattedPrice, quantity, description, category);

    }

    @Override
    public int compareTo(Product o) {
        return (int) (-this.getPrice() + o.getPrice());
    }
}
