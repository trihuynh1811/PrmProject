package com.example.prmproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prmproject.R;
import com.example.prmproject.api.APIClient;
import com.example.prmproject.dto.AuthenticationRequest;
import com.example.prmproject.dto.AuthenticationResponse;
import com.example.prmproject.service.AuthService;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText username, password, email, phone, confirmPassword;
    Button registerBtn;
    TextView tologin;
    AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        authService = APIClient.getClient().create(AuthService.class);

        username = findViewById(R.id.usernameEditTxt);
        password = findViewById(R.id.passwordEditText);
        email = findViewById(R.id.emailEditTxt);
        phone = findViewById(R.id.etPhone);
        confirmPassword = findViewById(R.id.etConfirmpassword);
        registerBtn = findViewById(R.id.registerBtn);
        tologin = findViewById(R.id.tologin);

        tologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                return;
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().isEmpty()) {
                    username.setError("please enter username");
                    return;
                }
                if (password.getText().toString().isEmpty()) {
                    password.setError("please enter password");
                    return;
                }
                if (email.getText().toString().isEmpty()) {
                    email.setError("please enter email");
                    return;
                }
                if (phone.getText().toString().isEmpty()) {
                    phone.setError("please enter phone");
                    return;
                }
                if (confirmPassword.getText().toString().isEmpty()) {
                    confirmPassword.setError("please confirm your password");
                    return;
                }
                if (!password.getText().toString().equals(confirmPassword.getText().toString())) {
                    confirmPassword.setError("passwords do not match");
                    return;
                }

                AuthenticationRequest authRequest = new AuthenticationRequest(
                        username.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString(),
                        phone.getText().toString()
                );
                Log.d("RegisterRequest", new Gson().toJson(authRequest));

                Call<AuthenticationResponse> call = authService.register(authRequest);
                call.enqueue(new Callback<AuthenticationResponse>() {
                    @Override
                    public void onResponse(Call<AuthenticationResponse> call, Response<AuthenticationResponse> response) {
                        if (response.isSuccessful()) {
                            AuthenticationResponse authResponse = response.body();
                            if (authResponse != null && authResponse.getStatus().equals("Email is exist")) {
                                Toast.makeText(RegisterActivity.this, "Email is exist: ", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Registration successful: ", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            }
                        } else {
                            try {
                                String errorBody = response.errorBody().string();
                                Log.e("RegisterError", errorBody);
                                Toast.makeText(RegisterActivity.this, "Registration failed: " + errorBody, Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                                Log.e("RegisterError", "Error parsing error body", e);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AuthenticationResponse> call, Throwable t) {
                        Log.e("RegisterError", t.getMessage());
                        Toast.makeText(RegisterActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
