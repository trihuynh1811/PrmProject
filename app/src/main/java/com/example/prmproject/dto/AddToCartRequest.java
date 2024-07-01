package com.example.prmproject.dto;

public class AddToCartRequest {
    private int customerID;
    private int productID;

    public AddToCartRequest(int customerID, int productID) {
        this.customerID = customerID;
        this.productID = productID;
    }
}
