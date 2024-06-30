package com.example.prmproject.models;

import java.util.List;

public class Product {
    private int productID;
    private String productName;
    private String description;
    private double price;
    private int quantity;
    private int quantitySold;
    private boolean status;
    private List<ProductImages> productImages;

    public Product(int productID, String productName, String description, double price, int quantity, int quantitySold, boolean status, List<ProductImages> productImages) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.quantitySold = quantitySold;
        this.status = status;
        this.productImages = productImages;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<ProductImages> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImages> productImages) {
        this.productImages = productImages;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", quantitySold=" + quantitySold +
                ", status=" + status +
                ", productImages=" + productImages +
                '}';
    }
}
