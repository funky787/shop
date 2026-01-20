package model;

public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;
    private String image;
    private String description;

    public Product(int id, String name, double price, int stock, String image, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.image = image;
        this.description = description;

    }


    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getImage() { return image; }
    public String getDescription() { return description; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}