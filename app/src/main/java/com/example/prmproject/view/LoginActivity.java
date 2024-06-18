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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {
    EditText password, email;
    Button loginBtn;
    Connection connection;
    TextView toregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        password = findViewById(R.id.passwordEditText);
        email = findViewById(R.id.emailEditTxt);
        loginBtn = findViewById(R.id.loginBtn);
        toregister = findViewById(R.id.toregister);

        SqlConnection sqlConnection = new SqlConnection();
        connection = sqlConnection.CONN();
        sqlConnection.checkConnection(connection);

        toregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().isEmpty()){
                    password.setError("please enter password");
                    return;
                }
                if(email.getText().toString().isEmpty()){
                    email.setError("please enter email");
                    return;
                }

                try {
                    String sqlcmd = "select * from users where email = ? and password = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(sqlcmd);
                    preparedStatement.setString(1, email.getText().toString());
                    preparedStatement.setString(2, password.getText().toString());
                    ResultSet rs = preparedStatement.executeQuery();
                    if(rs.isBeforeFirst()){
                        while (rs.next()){
                            int userId = rs.getInt(1);
                            String accountName = rs.getString(2);
                            String email = rs.getString(3);
                            Log.i("user result", String.valueOf(userId) + " " + accountName + " " + email);
                        }
                        Toast.makeText(getApplicationContext(), "login successfully", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "wrong email or password", Toast.LENGTH_SHORT).show();
                    }
                    connection.close();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
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
