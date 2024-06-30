package com.example.prmproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmproject.R;
import com.example.prmproject.models.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product2, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        if (product != null) {
            // Set product data to views if they are not null
            if (holder.productName != null) {
                holder.productName.setText(product.getProductName());
            }
            if (holder.productDescription != null) {
                holder.productDescription.setText(product.getDescription());
            }
            if (holder.productPrice != null) {
                holder.productPrice.setText(product.getPrice() +"VNĐ");
            }

            // Load product image using Picasso
            if (holder.productImage != null) {
                if (product.getProductImages() != null && !product.getProductImages().isEmpty()) {
                    Picasso.get()
                            .load(product.getProductImages().get(0).getImageUrl())
                            .error(R.drawable.checkout_image_item)
                            .into(holder.productImage);
                } else {
                    holder.productImage.setImageResource(R.drawable.checkout_image_item); // Default placeholder
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return productList != null ? productList.size() : 0;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productDescription, productPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productDescription = itemView.findViewById(R.id.productDescription);
            productPrice = itemView.findViewById(R.id.productPrice);
        }
    }
}
