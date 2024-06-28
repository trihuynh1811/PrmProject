package com.example.prmproject.Model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Cart", foreignKeys = {
        @ForeignKey(entity = Product.class, parentColumns = "productId", childColumns = "productId"),
        @ForeignKey(entity = User.class, parentColumns = "userId", childColumns = "usersId")
})
public class Cart {
    @PrimaryKey(autoGenerate = true)
    private int cartId;
    private int productId;
    private int usersId;

    public Cart() {
    }

    public Cart(int cartId, int productId, int usersId) {
        this.cartId = cartId;
        this.productId = productId;
        this.usersId = usersId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }
}
