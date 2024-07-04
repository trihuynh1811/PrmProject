package com.example.prmproject.service;

import com.example.prmproject.models.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OrderService {

    String ORDER ="order";
    @GET(ORDER + "/{id}")
    Call<List<Order>> getOrders(@Path("id") int UserId);
}
