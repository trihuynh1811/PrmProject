package com.example.prmproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmproject.R;
import com.example.prmproject.adapter.OrderDetailAdapter;
import com.example.prmproject.models.Order;
import com.example.prmproject.models.OrderDetailDTO;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailActivity extends AppCompatActivity {
RecyclerView recView;
Button backToHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history_item);
        Order order = (Order) getIntent().getSerializableExtra("OrderDetail");
        recView = findViewById(R.id.order_item_recycler_view);
        backToHistory = findViewById(R.id.BackToOrderHistory);
        List<OrderDetailDTO> orderDetail =  new ArrayList<>(order.getOrderDetails());
        OrderDetailAdapter adapter = new OrderDetailAdapter(orderDetail,OrderDetailActivity.this);
        recView.setAdapter(adapter);

        recView.setLayoutManager(new LinearLayoutManager(OrderDetailActivity.this));
        backToHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderDetailActivity.this, OrderHistoryActivity.class);

                startActivity(intent);
                finish();
            }
        });

    }
}