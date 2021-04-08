package model;

import java.util.Date;

public abstract class Products {
    private String name;
    private String author;
    private Date yearOfPublication;
    private String category;
    private float price;

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

    public Date getYearOfPublication() {

        return yearOfPublication;
    }

    public void setYearOfPublication(Date yearOfPublication) {

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

    public Products(String name, String author, Date yearOfPublication, String category, float price) {
        this.name = name;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.category = category;
        this.price = price;
    }
    public Products() {

    }

    public abstract void addProduct();

}
