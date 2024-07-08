package com.example.prmproject.service;

import com.example.prmproject.dto.AddToCartRequest;
import com.example.prmproject.dto.CartResponse;
import com.example.prmproject.dto.CreateOrderRequest;
import com.example.prmproject.dto.OrderResponse;
import com.example.prmproject.models.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderService {

    String ORDER ="order";
    @GET(ORDER + "/{id}")
    Call<List<Order>> getOrders(@Path("id") int UserId);

    @POST(ORDER)
    Call<OrderResponse> createOrder(@Body CreateOrderRequest createOrderRequest);
}
