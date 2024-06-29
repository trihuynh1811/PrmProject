package com.example.prmproject.service;

import com.example.prmproject.dto.LoginRequest;
import com.example.prmproject.dto.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
}
