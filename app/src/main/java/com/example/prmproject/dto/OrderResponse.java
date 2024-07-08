package com.example.prmproject.dto;

import com.example.prmproject.models.Order;
import com.example.prmproject.models.OrderDetailDTO;

import java.util.List;

public class OrderResponse {
    private String status;
    private Order order;
    private List<OrderDetailDTO> orderDetails;
    private int statusCode;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
