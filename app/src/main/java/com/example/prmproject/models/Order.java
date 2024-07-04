package com.example.prmproject.models;

import java.io.Serializable;
import java.util.Set;

public class Order implements Serializable {
    public int orderId;
    public String orderDate;
    public double total;
    private String orderStatus;
    private Set<OrderDetailDTO> orderDetails;
    private String customerPhone;
    private String customerAddress;
    private String customerName;

    public Order(int orderID, String orderdate, double Total, String orderStatus, Set<OrderDetailDTO> orderDetails, String customerPhone, String customerAddress, String customerName) {
        this.orderId = orderID;
        this.orderDate = orderdate;
        this.total = Total;
        this.orderStatus = orderStatus;
        this.orderDetails = orderDetails;
        this.customerPhone = customerPhone;
        this.customerAddress = customerAddress;
        this.customerName = customerName;
    }

    public int getOrderID() {
        return orderId;
    }

    public void setOrderId(int orderID) {
        orderId = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderdate) {
        orderDate = orderdate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double Total) {
        total = Total;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Set<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
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
}
