package com.example.prmproject.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmproject.R;
import com.example.prmproject.adapter.OrderAdapter;
import com.example.prmproject.api.APIClient;
import com.example.prmproject.dto.LoginResponse;
import com.example.prmproject.models.Order;
import com.example.prmproject.service.OrderService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderHistoryActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    int userId;
    OrderService orderService;
    List<Order> orderList;
    Button backToMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        backToMenu = findViewById(R.id.BackToMenu);

   sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        userId =  sharedPreferences.getInt("userId",userId);

        orderService = APIClient.getClient().create(OrderService.class);
        // Lấy data từ login response

        // Set up RecyclerView
        fetchOrders();

        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderHistoryActivity.this, User_Page_Activity.class);

                startActivity(intent);
                finish();
            }
        });
    }
    private void fetchOrders() {
        Call<List<Order>> call = orderService.getOrders(userId);
        call.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {

                if (response.isSuccessful()) {
                    orderList = response.body();
                    if (orderList != null) {
                        RecyclerView recyclerView = findViewById(R.id.order_recycler_view);


                        OrderAdapter adapter = new OrderAdapter(orderList,OrderHistoryActivity.this,   sharedPreferences);
                        recyclerView.setAdapter(adapter);

                        recyclerView.setLayoutManager(new LinearLayoutManager(OrderHistoryActivity.this));
                    }
                } else {
                    Log.e("Order", "Response failed");
                    Log.e("Order", "Response failed with code: " + response.code());
                    Toast.makeText(OrderHistoryActivity.this, "Failed to fetch orders", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.e("Order", "API call failed", t);
                Toast.makeText(OrderHistoryActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}