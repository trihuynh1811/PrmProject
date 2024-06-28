package com.example.prmproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmproject.view.AdminViewOrderDetailActivity;
import com.example.prmproject.adapter.model.Order;
import com.example.prmproject.R;

import java.util.List;
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private Context context;
    private List<Order> orderList;

    public OrderAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.textViewOrderId.setText("Order ID: " + order.getOrderId());
        holder.textViewOrderDate.setText("Order Date: " + order.getOrderDate());
        holder.textViewTotal.setText("Total: " + order.getTotal());
        holder.textViewVoucherId.setText("Voucher ID: " + order.getVoucherId());
        holder.textViewCustomerName.setText("Customer Name: " + order.getCustomerName());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, AdminViewOrderDetailActivity.class);
            intent.putExtra("ORDER_ID", order.getOrderId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView textViewOrderId, textViewOrderDate, textViewTotal, textViewVoucherId, textViewCustomerName;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewOrderId = itemView.findViewById(R.id.textViewOrderId);
            textViewOrderDate = itemView.findViewById(R.id.textViewOrderDate);
            textViewTotal = itemView.findViewById(R.id.textViewTotal);
            textViewVoucherId = itemView.findViewById(R.id.textViewVoucherId);
            textViewCustomerName = itemView.findViewById(R.id.textViewCustomerName);
        }
    }
}
