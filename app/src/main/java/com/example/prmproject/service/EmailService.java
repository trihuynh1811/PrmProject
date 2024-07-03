package com.example.prmproject.service;

import com.example.prmproject.dto.EmailRequest;
import com.example.prmproject.dto.EmailResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface EmailService {
    @FormUrlEncoded
    @POST("auth/forgot-password")
    Call<ResponseBody> forgotPassword(@Field("email") String email);
}