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

import java.util.List;

public class HomeFragment extends Fragment {

    private static APIService apiService;
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
        apiService = RetrofitClient.getRetrofit().create(APIService.class);
        apiService.getCategoriesAll().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    categoryList = response.body();

                    categoryAdapter = new CategoryAdapter(HomeFragment.this, categoryList);
                    rvCate.setHasFixedSize(true);

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
                            getApplicationContext(),
                            LinearLayoutManager.HORIZONTAL,
                            false
                    );

                    rvCate.setLayoutManager(layoutManager);
                    rvCate.setAdapter(categoryAdapter);
                    categoryAdapter.notifyDataSetChanged();
                } else {
                    //int statusCode = response.code();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("logg", t.getMessage());
            }
        });
    }
}