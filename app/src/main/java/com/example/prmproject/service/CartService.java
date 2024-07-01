package com.example.prmproject.service;

import com.example.prmproject.models.Cart;
import com.example.prmproject.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CartService {
    String CART ="cart";
    @GET("cart/{id}")
    Call<List<Cart>> getCartForCustomer(@Path("id") int cartId);
}
