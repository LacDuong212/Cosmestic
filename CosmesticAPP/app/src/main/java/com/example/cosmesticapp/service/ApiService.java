package com.example.cosmesticapp.service;

import com.example.cosmesticapp.dto.ApiResponse;
import com.example.cosmesticapp.dto.RegisterRequest;
import com.example.cosmesticapp.dto.VerifyOTPRequest;
import com.example.cosmesticapp.model.Category;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api/auth/register")
    Call<ApiResponse> registerUser(@Body RegisterRequest registerRequest);

    @POST("api/auth/verify-otp")
    Call<ApiResponse> verifyOTP(@Body VerifyOTPRequest verifyOTPRequest);

    @GET("categories.php")
    Call<List<Category>> getAllCategories();
}