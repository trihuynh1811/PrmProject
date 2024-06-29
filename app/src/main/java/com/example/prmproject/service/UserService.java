package com.example.prmproject.service;

import com.example.prmproject.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {
    String USER = "user";
    @GET(USER)
    Call<List<User>> getAllUser();

    @GET(USER + "/{id}")
    Call<User> getTraineeById(@Path("id") Object id);

    @POST(USER)
    Call<User> createTrainee(@Body User trainee);

    @PUT(USER + "/{id}")
    Call<User> updateTrainee(@Path("id") Object id, @Body User trainee);

    @DELETE(USER + "/{id}")
    Call<User> deleteTrainee(@Path("id") Object id);
}
