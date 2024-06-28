package com.example.prmproject.Model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "OrderDetail", foreignKeys = {
        @ForeignKey(entity = Product.class, parentColumns = "productId", childColumns = "productId"),
        @ForeignKey(entity = Order.class, parentColumns = "orderId", childColumns = "orderId")
})
public class OrderDetail {
    @PrimaryKey(autoGenerate = true)
    private int orderDetailId;
    private int productId;
    private int quantity;
    private double price;
    private int orderId;

    public OrderDetail() {
    }

    public OrderDetail(int productId, int quantity, double price, int orderId) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.orderId = orderId;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
