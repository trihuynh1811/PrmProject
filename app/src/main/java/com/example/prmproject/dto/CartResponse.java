package com.example.prmproject.dto;

public class CartResponse {
    private String status;
    private Cart cart;

    public String getStatus() {
        return status;
    }

    public Cart getCart() {
        return cart;
    }

    public class Cart {
        private int cartID;
        private int quantity;

        public int getCartID() {
            return cartID;
        }

        public int getQuantity() {
            return quantity;
        }
    }
}
