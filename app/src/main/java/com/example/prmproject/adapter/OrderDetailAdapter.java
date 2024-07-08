package com.example.prmproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmproject.R;
import com.example.prmproject.models.OrderDetailDTO;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderDetailAdapter extends  RecyclerView.Adapter<OrderDetailAdapter.OrderDetailViewHolder>{

    private Context context;

    public List<OrderDetailDTO> orderList;

    public OrderDetailAdapter(List<OrderDetailDTO> orderList, Context context) {
        this.context = context;
        this.orderList = orderList;

    }

    @NonNull
    @Override
    public OrderDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_detail, parent, false);
        return new OrderDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailViewHolder holder, int position) {
        OrderDetailDTO orderDetailitem = orderList.get(position);
        if (orderDetailitem != null){
            holder.ProductPrice.setText("Product Price : " + Double.toString(orderDetailitem.getPrice()));
            holder.ProductName.setText("Product Name : " + orderDetailitem.getItemName());
            holder.Quantity.setText("Quantity : " + Integer.toString(orderDetailitem.getQuantity()));
            if (orderDetailitem.getItemImages() != null && !orderDetailitem.getItemImages().isEmpty()) {
                Picasso.get()
                        .load(orderDetailitem.getItemImages())
                        .error(R.drawable.checkout_image_item)
                        .into(holder.ProductImage);
            } else {
                holder.ProductImage.setImageResource(R.drawable.checkout_image_item); // Default placeholder
            }
        }
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class OrderDetailViewHolder extends RecyclerView.ViewHolder {
        ImageView ProductImage;
        public TextView ProductName,ProductPrice,Quantity;
        public OrderDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            ProductName = itemView.findViewById(R.id.textViewProductName);
            Quantity = itemView.findViewById(R.id.textViewProductQuantity);
            ProductPrice = itemView.findViewById(R.id.textViewPrice);
            ProductImage = itemView.findViewById(R.id.imageViewProductImage);
        }
    }
}
