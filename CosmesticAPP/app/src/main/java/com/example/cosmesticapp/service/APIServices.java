package com.example.cosmesticapp.service;

import com.example.cosmesticapp.model.Category;

import java.util.List;

public class APIServices {
    @GET("categories.php")
    Call<List<Category>> getAllCategories();
}
