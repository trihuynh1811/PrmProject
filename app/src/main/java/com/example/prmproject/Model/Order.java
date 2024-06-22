package com.example.prmproject.Model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Orders", foreignKeys = {
        @ForeignKey(entity = Voucher.class, parentColumns = "voucherId", childColumns = "voucherId"),
        @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "customerId")
})
public class Order {
    @PrimaryKey(autoGenerate = true)
    private int orderId;
    private String orderDate;
    private double total;
    private int voucherId;
    private int customerId;

    public Order() {
    }

    public Order(int orderId, String orderDate, double total, int voucherId, int customerId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.total = total;
        this.voucherId = voucherId;
        this.customerId = customerId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(int voucherId) {
        this.voucherId = voucherId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
