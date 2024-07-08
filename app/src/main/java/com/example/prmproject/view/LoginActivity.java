package com.example.prmproject.view;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.prmproject.dto.LoginRequest;
import com.example.prmproject.dto.LoginResponse;
import com.example.prmproject.service.AuthService;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText password, email;
    Button loginBtn;
    TextView toregister,forgotPass;
    AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        authService = APIClient.getClient().create(AuthService.class);

        password = findViewById(R.id.passwordEditText);
        email = findViewById(R.id.emailEditTxt);
        loginBtn = findViewById(R.id.loginBtn);
        toregister = findViewById(R.id.toregister);
        forgotPass = findViewById(R.id.forgotpass);
        toregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ForgotPassword.class));
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getText().toString().isEmpty()) {
                    password.setError("please enter password");
                    return;
                }
                if (email.getText().toString().isEmpty()) {
                    email.setError("please enter email");
                    return;
                }

                LoginRequest loginRequest = new LoginRequest(email.getText().toString(), password.getText().toString());
                Log.d("LoginRequest", new Gson().toJson(loginRequest));

                Call<LoginResponse> call = authService.login(loginRequest);
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            LoginResponse loginResponse = response.body();
                            Log.d("LoginResponse", loginResponse != null ? loginResponse.toString() : "No response body");
                            if(loginResponse != null && loginResponse.getUserInfo().getRole().equals("CUSTOMER")){
                                //Save login response
                                SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putInt("userId",loginResponse.getUserInfo().getUsersID());
                                editor.putString("name",loginResponse.getUserInfo().getAccountName());
                                editor.putString("email", loginResponse.getUserInfo().getEmail());
                                editor.putString("phone", loginResponse.getUserInfo().getPhone());
                                editor.putString("access_token", loginResponse.getAccess_token());
                                editor.putString("refresh_token", loginResponse.getRefresh_token());
                                editor.apply();
                                Toast.makeText(LoginActivity.this, "Login successful: ", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("loginResponse", loginResponse);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(LoginActivity.this, "This account is not a customer ", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            try {
                                String errorBody = response.errorBody().string();
                                Log.e("LoginError", errorBody);
                                Toast.makeText(LoginActivity.this, "Login failed: " + errorBody, Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                                Log.e("LoginError", "Error parsing error body", e);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.e("LoginError", t.getMessage());
                        Toast.makeText(LoginActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
