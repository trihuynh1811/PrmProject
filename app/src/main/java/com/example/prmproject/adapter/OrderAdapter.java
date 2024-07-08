package com.example.prmproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmproject.R;
import com.example.prmproject.dto.LoginResponse;
import com.example.prmproject.models.Order;
import com.example.prmproject.view.OrderDetailActivity;


import java.text.SimpleDateFormat;
import java.util.List;

public class OrderAdapter extends  RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{

    SharedPreferences sharedPreferences;
    String pattern = "yyyy-MM-dd HH:mm";

    private Context context;
    SimpleDateFormat df = new SimpleDateFormat(pattern);
    public List<Order> orderList;

    public OrderAdapter( List<Order> orderList,Context context,    SharedPreferences sharedPreferences) {
        this.context = context;
        this.orderList = orderList;
        this.sharedPreferences = sharedPreferences;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order orderItem = orderList.get(position);
        String username = "";
        username =  sharedPreferences.getString("name",username);
        if (orderItem != null) {
            // khả năng bug
            holder.OrderID.setText("Order ID : " + Integer.toString(orderItem.getOrderID()));
            holder.Total.setText("Total Price : " + Double.toString(orderItem.getTotal()));
            holder.OrderDate.setText("Order Date : " + orderItem.getOrderDate());
            holder.OrderStatus.setText("Order Status : " + orderItem.getOrderStatus());
            holder.CustomerName.setText("Customer Name : " + username);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, OrderDetailActivity.class);

                    intent.putExtra("OrderDetail",orderItem);
                    context.startActivity(intent);

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        public TextView OrderID,OrderStatus,Total,OrderDate,CustomerName;
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            OrderID = itemView.findViewById(R.id.textViewOrderId);
            OrderStatus = itemView.findViewById(R.id.textViewOrderStatus);
            Total = itemView.findViewById(R.id.textViewTotal);
            OrderDate = itemView.findViewById(R.id.textViewOrderDate);
            CustomerName = itemView.findViewById(R.id.textViewCustomerName);
        }
    }
}
