//22110304 - Võ Nguyễn Hòa Lạc Dương
//22110311 - Tô Hữu Đức
package com.example.cosmesticapp.service;

import com.example.cosmesticapp.dto.ApiResponse;
import com.example.cosmesticapp.dto.RegisterRequest;
import com.example.cosmesticapp.dto.VerifyOTPRequest;
import com.example.cosmesticapp.model.Category;
import com.example.cosmesticapp.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST("api/auth/register")
    Call<ApiResponse> registerUser(@Body RegisterRequest registerRequest);

    @POST("api/auth/verify-otp")
    Call<ApiResponse> verifyOTP(@Body VerifyOTPRequest verifyOTPRequest);

    @GET("api/categories")
    Call<List<Category>> getAllCategories();

    @GET("api/products/category/{categoryId}/price-asc")
    Call<List<Product>> getProductsByCategoryOrderByPriceAsc(@Path("categoryId") int categoryId);
}