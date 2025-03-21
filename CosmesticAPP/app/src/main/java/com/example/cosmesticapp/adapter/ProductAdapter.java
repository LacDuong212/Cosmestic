// 22110304 - Võ Nguyễn Hòa Lạc Dương
// 22110459 - Trần Triệu Vĩ
package com.example.cosmesticapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cosmesticapp.R;
import com.example.cosmesticapp.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Product> productList;
    private final int VIEW_TYPE_PRODUCT = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getItemViewType(int position) {
        return productList.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_PRODUCT;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_PRODUCT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
            return new MyViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            Product product = productList.get(position);
            myViewHolder.productName.setText(product.getProductName());

            Glide.with(context)
                    .load(product.getImage())
                    .into(myViewHolder.productImage);

            myViewHolder.itemView.setOnClickListener(v ->
                    Toast.makeText(context, "Bạn đã chọn sản phẩm: " + product.getProductName(), Toast.LENGTH_SHORT).show()
            );
        } else {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return productList == null ? 0 : productList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView productImage;
        public TextView productName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.cateProductImage);
            productName = itemView.findViewById(R.id.cateName);
        }
    }

    private static class LoadingViewHolder extends RecyclerView.ViewHolder {
        ProgressBar progressBar;
        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar2);
        }
    }
}