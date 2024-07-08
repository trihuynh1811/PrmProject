package com.example.prmproject.service;

import com.example.prmproject.models.Order;
import com.example.prmproject.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductService {

    String PRODUCT ="product";
    @GET(PRODUCT)
    Call<List<Product>> getProducts();

    @GET("product/{id}")
    Call<Product> getProductById(@Path("id") int id);
}
