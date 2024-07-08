package com.example.prmproject.dto;

public class OrderProductRequest {
    private int productId;
    private int quantity;

    public OrderProductRequest() {
    }

    public OrderProductRequest(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }
}
