package com.example.prmproject.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prmproject.R;
import com.example.prmproject.dto.LoginResponse;

public class User_Page_Activity extends AppCompatActivity {
    LoginResponse loginResponse;
    TextView tvName,tvEmail;
    Button logout,btnHome,btnAddress,btnOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_page);
        logout = findViewById(R.id.btnLogout);
        btnHome =findViewById(R.id.btnHome);
        btnAddress = findViewById(R.id.btnAddress);
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        btnOrder = findViewById(R.id.btnOrder);

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", null);
        String name = sharedPreferences.getString("name",null);
        String accessToken = sharedPreferences.getString("access_token", null);
        String refreshToken = sharedPreferences.getString("refresh_token", null);
        // Lấy data từ login response
        loginResponse = getIntent().getParcelableExtra("loginResponseUser");

        if (loginResponse != null) {
            Log.d("User_Page_Activity", "LoginResponse received: " + loginResponse.toString());
        } else {
            Log.e("User_Page_Activity", "No LoginResponse received");
        }
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Page_Activity.this, OrderHistoryActivity.class);

                startActivity(intent);

            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Page_Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        tvName.setText(name);
        tvEmail.setText(email);
        logout.setOnClickListener(v -> {
            new AlertDialog.Builder(User_Page_Activity.this)
                    .setTitle("Thoát")
                    .setMessage("Bạn có chắc chắn muốn thoát ứng dụng?")
                    .setPositiveButton("Thoát", (d, w) -> {
                        // delete user response
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.apply();
                        Intent intent = new Intent(User_Page_Activity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    })
                    .setNegativeButton("Huỷ", null)
                    .show();
        });

        btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Page_Activity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

    }
}