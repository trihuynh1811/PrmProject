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
import com.example.prmproject.service.EmailService;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword extends AppCompatActivity {
    private static final String TAG = "ForgotPassword";

    TextView rollbackLogin;
    Button sendBtn;
    EditText emailEditTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        rollbackLogin = findViewById(R.id.rollbackLogin);
        emailEditTxt = findViewById(R.id.emailEditTxt);
        sendBtn = findViewById(R.id.sendBtn);

        rollbackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditTxt.getText().toString().trim();
                if (!email.isEmpty()) {
                    sendForgotPasswordRequest(email);
                } else {
                    Toast.makeText(ForgotPassword.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendForgotPasswordRequest(String email) {
        EmailService emailService = APIClient.getClient().create(EmailService.class);
        Call<ResponseBody> call = emailService.forgotPassword(email);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String responseBody = response.body().string();
                        Log.d(TAG, "Response body: " + responseBody);
                        Toast.makeText(ForgotPassword.this, "New password is send to your email", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    } catch (IOException e) {
                        Log.e(TAG, "Failed to read response body", e);
                        Toast.makeText(ForgotPassword.this, "Failed to read response body", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e(TAG, "Failed to send email. Response code: " + response.code() + ", message: " + response.message());
                    Toast.makeText(ForgotPassword.this, "Failed to send email", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "An error occurred: " + t.getMessage(), t);
                Toast.makeText(ForgotPassword.this, "An error occurred: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}