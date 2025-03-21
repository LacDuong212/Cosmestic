package com.example.cosmesticapp.Fragments;

import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cosmesticapp.R;
import com.example.cosmesticapp.adapter.CategoryAdapter;
import com.example.cosmesticapp.model.Category;
import com.example.cosmesticapp.service.ApiService;
import com.example.cosmesticapp.service.RetrofitClient;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private static ApiService apiService;
    private static List<Category> categoryList;
    private static CategoryAdapter categoryAdapter;
    private static RecyclerView rvCate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        getCategory();
        return view;
    }

    private void getCategory() {
        rvCate = rvCate.findViewById(R.id.rvCategory);
        apiService = RetrofitClient.getClient("http://localhost:8080/api/categories").create(ApiService.class);
        apiService.getAllCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Category>> call, Response<List<Category>> response) {
                categoryList = response.body();
                categoryAdapter = new CategoryAdapter(getContext(), categoryList);
                rvCate.setAdapter(categoryAdapter);
                rvCate.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(retrofit2.Call<List<Category>> call, Throwable t) {
            }
        } );
    }

    private
}