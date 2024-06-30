package com.example.prmproject.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.prmproject.R;
import com.example.prmproject.dto.LoginResponse;
import com.example.prmproject.models.User;

public class MainActivity extends AppCompatActivity {

    ImageButton profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Lấy data từ login response
        LoginResponse loginResponse = getIntent().getParcelableExtra("loginResponse");

        if (loginResponse != null) {
            Log.d("MainActivity", "LoginResponse received: " + loginResponse.toString());
        } else {
            Log.e("MainActivity", "No LoginResponse received");
        }

        profile = findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, User_Page_Activity.class);
                intent.putExtra("loginResponse", loginResponse);
                startActivity(intent);
            }
        });





    }
}