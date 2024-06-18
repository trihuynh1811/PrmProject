package com.example.prmproject.view;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmproject.R;
import com.example.prmproject.adapter.OrderAdapter;
import com.example.prmproject.model.Order;

import java.util.ArrayList;
import java.util.List;

public class AdminViewOrderActivity extends AppCompatActivity {
    private RecyclerView recyclerViewOrders;
    private OrderAdapter orderAdapter;
    private List<Order> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_order);

        recyclerViewOrders = findViewById(R.id.recyclerViewOrders);
        recyclerViewOrders.setLayoutManager(new LinearLayoutManager(this));

        // Giả lập dữ liệu đơn hàng
        orderList = new ArrayList<>();
        orderList.add(new Order("12345", "01/01/2023", "$100.00", "VOUCHER123", "John Doe"));
        orderList.add(new Order("12346", "02/01/2023", "$200.00", "VOUCHER456", "Jane Doe"));

        orderAdapter = new OrderAdapter(this, orderList);
        recyclerViewOrders.setAdapter(orderAdapter);
    }
}
