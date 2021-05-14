package model;


import java.util.Date;
import java.util.List;

public abstract class Product {
    private String id;
    private String name;
    private String author;
    private String yearOfPublication;
    private String category;
    private float price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getAuthor() {

        return author;
    }

    public void setAuthor(
            String author) {

        this.author = author;
    }

    public String getYearOfPublication() {

        return yearOfPublication;
    }

    public void setYearOfPublication(String yearOfPublication) {

        this.yearOfPublication = yearOfPublication;
    }

    public String getCategory() {

        return category;
    }

    public void setCategory(String category) {

        this.category = category;
    }

    public float getPrice() {

        return price;
    }

    public void setPrice(float price) {

        this.price = price;
    }

    public Product(String id, String name, String author, String yearOfPublication, String category, float price) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.category = category;
        this.price = price;
    }

    public Product() {

    }

    public Product(String name, String ID) {
        this.name = name;
        this.id = ID;
    }

    public abstract void addProduct(Product product);

    public abstract void editProduct(Product product);

    public abstract void deleteProduct(Product product);

    public abstract List<Product> searchProduct(String key, int criterion);


}
