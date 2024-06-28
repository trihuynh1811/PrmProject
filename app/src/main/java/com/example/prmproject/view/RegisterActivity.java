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

import com.example.prmproject.Database.SqlServer.SqlConnection;
import com.example.prmproject.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegisterActivity extends AppCompatActivity {
    EditText username, password, email;
    Button registerBtn;
    Connection connection;
    TextView tologin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.usernameEditTxt);
        password = findViewById(R.id.passwordEditText);
        email = findViewById(R.id.emailEditTxt);
        registerBtn = findViewById(R.id.registerBtn);
        tologin = findViewById(R.id.tologin);

        SqlConnection sqlConnection = new SqlConnection();
        connection = sqlConnection.CONN();
        sqlConnection.checkConnection(connection);

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
                if(username.getText().toString().isEmpty()){
                    username.setError("please enter username");
                    return;
                }
                if(password.getText().toString().isEmpty()){
                    password.setError("please enter password");
                    return;
                }
                if(email.getText().toString().isEmpty()){
                    email.setError("please enter email");
                    return;
                }

                try {
                    String sqlcmd = "insert into users(accountName, email, password) values(?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(sqlcmd);
                    preparedStatement.setString(1, username.getText().toString());
                    preparedStatement.setString(2, email.getText().toString());
                    preparedStatement.setString(3, password.getText().toString());
                    boolean success = preparedStatement.execute();
                    Log.i("insert result", String.valueOf(success));
                    Toast.makeText(getApplicationContext(), "new user created", Toast.LENGTH_SHORT).show();
                    connection.close();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void connect(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try{
                if(connection == null){
                    Log.e("error connect to sql server", "error");
                }
                else{
                    Log.e("successfully connect to sql server", "connected");
                }
            }catch(Exception e){
                throw new RuntimeException(e);
            }

            runOnUiThread(() -> {
                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    throw new RuntimeException(e);
                }
            });
        });
    }
}