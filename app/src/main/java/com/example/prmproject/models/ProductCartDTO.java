package com.example.prmproject.models;

import com.example.prmproject.models.ProductImages;

import java.util.List;

public class ProductCartDTO {
    private int productID;
    private String productName;
    private String description;
    private double price;
    private List<ProductImages> productImages;

    public ProductCartDTO(int productID, String productName, String description, double price, List<ProductImages> productImages) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.price = price;
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

    public List<ProductImages> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImages> productImages) {
        this.productImages = productImages;
    }

    @Override
    public String toString() {
        return "ProductCartDTO{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", productImages=" + productImages +
                '}';
    }
}
