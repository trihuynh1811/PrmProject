package com.example.prmproject.service;

import com.example.prmproject.models.Category;
import com.example.prmproject.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoryService {
    String CATEGORY = "category";
    @GET(CATEGORY)
    Call<List<Category>> getAllCategory();
}
