package com.example.prmproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private OnAddToCartClickListener addToCartClickListener;

    public ProductAdapter(List<Product> productList, OnAddToCartClickListener listener) {
        this.productList = productList;
        this.addToCartClickListener = listener;
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
            holder.productName.setText(product.getProductName());
            holder.productDescription.setText(product.getDescription());
            holder.productPrice.setText(product.getPrice() + "VNĐ");

            // Load product image using Picasso
            if (product.getProductImages() != null && !product.getProductImages().isEmpty()) {
                Picasso.get()
                        .load(product.getProductImages().get(0).getImageUrl())
                        .error(R.drawable.checkout_image_item)
                        .into(holder.productImage);
            } else {
                holder.productImage.setImageResource(R.drawable.checkout_image_item); // Default placeholder
            }

            // Lưu productID vào ViewHolder
            holder.productID = product.getProductID();

            // Handle click on addToCartButton
            holder.addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (addToCartClickListener != null) {
                        addToCartClickListener.onAddToCartClick(holder.productID);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return productList != null ? productList.size() : 0;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productDescription, productPrice;
        Button addToCart;
        int productID;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productDescription = itemView.findViewById(R.id.productDescription);
            productPrice = itemView.findViewById(R.id.productPrice);
            addToCart = itemView.findViewById(R.id.addToCartButton);
        }
    }

    // Interface to handle click on addToCartButton
    public interface OnAddToCartClickListener {
        void onAddToCartClick(int productID);
    }
}
