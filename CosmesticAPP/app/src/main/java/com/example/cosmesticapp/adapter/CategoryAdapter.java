//22110304 - Võ Nguyễn Hòa Lạc Dương
//22110311 - Tô Hữu Đức
package com.example.cosmesticapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cosmesticapp.R;
import com.example.cosmesticapp.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {
    private Context context;
    private List<Category> categoryList;
    private OnCategoryClickListener onCategoryClickListener;

    // Interface để xử lý sự kiện click
    public interface OnCategoryClickListener {
        void onCategoryClick(int categoryId);
    }

    public CategoryAdapter(Context context, List<Category> categoryList, OnCategoryClickListener listener) {
        this.context = context;
        this.categoryList = categoryList;
        this.onCategoryClickListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.tenSp.setText(category.getCategoryName());

        // Load image using Glide, Picasso, or another image loading library
        // Assuming your Category class has a method getImageUrl() or getImagePath()
        if (category.getImage() != null && !category.getImage().isEmpty()) {
            // Using Glide (add this dependency if not already added)
            Glide.with(context)
                    .load(category.getImage())
                    .into(holder.imageView);
        }

        // Xử lý sự kiện click
        holder.itemView.setOnClickListener(v -> {
            if (onCategoryClickListener != null) {
                onCategoryClickListener.onCategoryClick(category.getCategoryId().intValue());
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tenSp;
        public ImageView imageView; // Add this line


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tenSp = itemView.findViewById(R.id.cateName);
            imageView = itemView.findViewById(R.id.cateImage); // Add this line
        }
    }
}