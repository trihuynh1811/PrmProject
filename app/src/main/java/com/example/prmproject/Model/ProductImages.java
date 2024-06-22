package com.example.prmproject.Model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "ProductImages", foreignKeys = @ForeignKey(entity = Product.class, parentColumns = "productId", childColumns = "productId", onDelete = ForeignKey.CASCADE))
public class ProductImages {
    @PrimaryKey
    private int imageId;
    private String url;
    private int productId;

    public ProductImages() {
    }

    public ProductImages(String url, int productId) {
        this.url = url;
        this.productId = productId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
