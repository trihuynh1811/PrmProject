package com.example.prmproject;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class AdminViewOrderDetailActivity extends AppCompatActivity {
    private TextView textViewOrderDetailIdValue, textViewProductIdValue, textViewQuantityValue, textViewPriceValue, textViewOrderIdValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view_order_detail);

        textViewOrderDetailIdValue = findViewById(R.id.textViewOrderDetailIdValue);
        textViewProductIdValue = findViewById(R.id.textViewProductIdValue);
        textViewQuantityValue = findViewById(R.id.textViewQuantityValue);
        textViewPriceValue = findViewById(R.id.textViewPriceValue);
        textViewOrderIdValue = findViewById(R.id.textViewOrderIdValue);

        String orderId = getIntent().getStringExtra("ORDER_ID");
        String orderDetailId = "OD12345";
        String productId = "P12345";
        String quantity = "10";
        String price = "$100.00";

        textViewOrderDetailIdValue.setText(orderDetailId);
        textViewProductIdValue.setText(productId);
        textViewQuantityValue.setText(quantity);
        textViewPriceValue.setText(price);
        textViewOrderIdValue.setText(orderId);
    }
}
