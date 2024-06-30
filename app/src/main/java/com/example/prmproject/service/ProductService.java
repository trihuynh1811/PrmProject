package com.example.prmproject.service;

import com.example.prmproject.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {

    String PRODUCT ="product";
    @GET(PRODUCT)
    Call<List<Product>> getProducts();
}
