package com.example.prmproject.Model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Product", foreignKeys = @ForeignKey(entity = Category.class, parentColumns = "categoryId", childColumns = "categoryId"))
public class Product {
    @PrimaryKey(autoGenerate = true)
    private int productId;
    private String productName;
    private double productPrice;
    private String productDescription;
    private float productRate;
    private int commentCount;
    private int countUsage;
    private int quantity;
    private int categoryId;

    public Product() {
    }

    public Product(String productName, double productPrice, int categoryId) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.categoryId = categoryId;
    }

    public Product(int productId, String productName, double productPrice, String productDescription, float productRate, int commentCount, int countUsage, int quantity, int categoryId) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.productRate = productRate;
        this.commentCount = commentCount;
        this.countUsage = countUsage;
        this.quantity = quantity;
        this.categoryId = categoryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public float getProductRate() {
        return productRate;
    }

    public void setProductRate(float productRate) {
        this.productRate = productRate;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getCountUsage() {
        return countUsage;
    }

    public void setCountUsage(int countUsage) {
        this.countUsage = countUsage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
