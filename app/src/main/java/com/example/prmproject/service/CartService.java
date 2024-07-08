package com.example.prmproject.service;

import com.example.prmproject.dto.AddToCartRequest;
import com.example.prmproject.dto.CartResponse;
import com.example.prmproject.models.Cart;
import com.example.prmproject.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CartService {
    String CART ="cart";
    @GET("cart/{id}")
    Call<List<Cart>> getCartForCustomer(@Path("id") int cartId);
    @POST(CART)
    Call<CartResponse> addToCart(@Body AddToCartRequest addToCartRequest);
    @DELETE(CART + "/{id}")
    Call<Void> deleteCartItem(@Path("id") int cartId);

    @PUT(CART+"/up/{cartId}")
    Call<Cart> upQuantity(@Path("cartId") int cartId);

    @PUT(CART+"/down/{cartId}")
    Call<Cart> downQuantity(@Path("cartId") int cartId);

    @GET(CART+"/quantityCart/{id}")
    Call<Integer> getQuantityInCart(@Path("id") int id);
}
