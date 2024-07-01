package com.example.prmproject.models;

public class ProductImages {
    private int productImageId;
    private String imageUrl;

    public ProductImages(int productImageId, String imageUrl) {
        this.productImageId = productImageId;
        this.imageUrl = imageUrl;
    }

    public int getProductImageId() {
        return productImageId;
    }

    public void setProductImageId(int productImageId) {
        this.productImageId = productImageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "ProductImages{" +
                "productImageId=" + productImageId +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
