package com.example.prmproject.models;


public class Cart {
    private int id;
    private ProductCartDTO productCartDTO;
    private int quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductCartDTO getProductCartDTO() {
        return productCartDTO;
    }

    public void setProductCartDTO(ProductCartDTO productCartDTO) {
        this.productCartDTO = productCartDTO;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", productCartDTO=" + productCartDTO +
                ", quantity=" + quantity +
                '}';
    }
}
