package com.example.prmproject.adapter.model;

public class Order {
    private String orderId;
    private String orderDate;
    private String total;
    private String voucherId;
    private String customerName;

    // Constructor, getters, and setters
    public Order(String orderId, String orderDate, String total, String voucherId, String customerName) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.total = total;
        this.voucherId = voucherId;
        this.customerName = customerName;
    }

    public String getOrderId() { return orderId; }
    public String getOrderDate() { return orderDate; }
    public String getTotal() { return total; }
    public String getVoucherId() { return voucherId; }
    public String getCustomerName() { return customerName; }
}
