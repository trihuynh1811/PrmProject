package com.example.prmproject.dto;

import java.util.List;

public class CreateOrderRequest {
    private double total;
    private int customerId;
    private String customerPhone;
    private String customerAddress;
    private String customerName;
    private List<OrderProductRequest> products;

    public CreateOrderRequest() {
    }

    public CreateOrderRequest(double total, int customerId, String customerPhone, String customerAddress, String customerName, List<OrderProductRequest> products) {
        this.total = total;
        this.customerId = customerId;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.customerName = customerName;
        this.products = products;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<OrderProductRequest> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProductRequest> products) {
        this.products = products;
    }
}

