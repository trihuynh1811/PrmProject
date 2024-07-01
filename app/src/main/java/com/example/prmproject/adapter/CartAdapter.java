package com.example.prmproject.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmproject.R;
import com.example.prmproject.models.Cart;
import com.example.prmproject.models.ProductCartDTO;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<Cart> cartList;
    private OnDeleteClickListener onDeleteClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(int cartId, int position);
    }

    public CartAdapter(List<Cart> cartList, OnDeleteClickListener onDeleteClickListener) {
        this.cartList = cartList;
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cartItem = cartList.get(position);
        ProductCartDTO product = cartItem.getProductCartDTO();
        if (product != null) {
            holder.productName.setText(product.getProductName());
            holder.productPrice.setText(String.valueOf(product.getPrice()));
            holder.productQuantity.setText(String.valueOf(cartItem.getQuantity()));

            if (product.getProductImages() != null && !product.getProductImages().isEmpty()) {
                String imageUrl = product.getProductImages().get(0).getImageUrl();
                Picasso.get().load(imageUrl).into(holder.productImage);
            } else {
                holder.productImage.setImageResource(R.drawable.checkout_image_item);
            }

            holder.ivDelete.setOnClickListener(v -> {
                if (onDeleteClickListener != null) {
                    onDeleteClickListener.onDeleteClick(cartItem.getId(), position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public void removeItem(int position) {
        cartList.remove(position);
        notifyItemRemoved(position);
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice, productQuantity;
        ImageView productImage, ivDelete;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.tvName);
            productPrice = itemView.findViewById(R.id.tvPrice);
            productQuantity = itemView.findViewById(R.id.tvQuantity);
            productImage = itemView.findViewById(R.id.ivAvatar);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }
    }
}