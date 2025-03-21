//22110304 - Võ Nguyễn Hòa Lạc Dương
//22110459 - Trần Triệu Vĩ
package com.example.cosmesticapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cosmesticapp.R;
import com.example.cosmesticapp.adapter.CategoryAdapter;
import com.example.cosmesticapp.adapter.ProductAdapter;
import com.example.cosmesticapp.model.Category;
import com.example.cosmesticapp.model.Product;
import com.example.cosmesticapp.service.ApiService;
import com.example.cosmesticapp.service.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements CategoryAdapter.OnCategoryClickListener {

    private ApiService apiService;
    private List<Category> categoryList;
    private CategoryAdapter categoryAdapter;

    private ProductAdapter productAdapter;
    private RecyclerView rvCate, rvProd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Khởi tạo RecyclerView
        rvCate = view.findViewById(R.id.rvCategory);
        rvProd = view.findViewById(R.id.rvProduct);

        // Gọi API để lấy danh sách danh mục
        getCategory();

        return view;
    }

    private void getCategory() {
        apiService = RetrofitClient.getClient("http://192.168.162.212:8080/").create(ApiService.class);
        apiService.getAllCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    categoryList = response.body();
                    // Truyền `this` (HomeFragment) làm listener cho CategoryAdapter
                    categoryAdapter = new CategoryAdapter(getContext(), categoryList, HomeFragment.this);
                    rvCate.setAdapter(categoryAdapter);
                    rvCate.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                } else {
                    Toast.makeText(getContext(), "Failed to load categories", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getProductsByCategoryOrderByPriceAsc(int categoryId) {
        apiService = RetrofitClient.getClient("http://192.168.162.212:8080/").create(ApiService.class);
        apiService.getProductsByCategoryOrderByPriceAsc(categoryId).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Product> productList = response.body();
                    productAdapter = new ProductAdapter(getContext(), productList);
                    rvProd.setAdapter(productAdapter);
                    rvProd.setLayoutManager(new GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false));
                } else {
                    Toast.makeText(getContext(), "Failed to load products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Xử lý sự kiện click từ CategoryAdapter
    @Override
    public void onCategoryClick(int categoryId) {
        // Gọi hàm lấy sản phẩm theo categoryId
        getProductsByCategoryOrderByPriceAsc(categoryId);
    }
}