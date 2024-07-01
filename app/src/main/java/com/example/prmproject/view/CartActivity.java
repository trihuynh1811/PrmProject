package com.example.prmproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmproject.R;
import com.example.prmproject.adapter.CartAdapter;
import com.example.prmproject.api.APIClient;
import com.example.prmproject.dto.LoginResponse;
import com.example.prmproject.models.Cart;
import com.example.prmproject.service.CartService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CartActivity extends AppCompatActivity implements CartAdapter.OnDeleteClickListener {
    private RecyclerView rvItemCart;
    private CartAdapter cartAdapter;
    private CartService cartService;
    private LoginResponse loginResponse;
    private TextView tvHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        rvItemCart = findViewById(R.id.rvItemCart);
        rvItemCart.setLayoutManager(new LinearLayoutManager(this));
        tvHome = findViewById(R.id.tvHome);
        loginResponse = getIntent().getParcelableExtra("loginResponseCart");
        if (loginResponse != null) {
            Log.d("Cart", "LoginResponse received: " + loginResponse.toString());
        } else {
            Log.e("Cart", "No LoginResponse received");
        }

        Retrofit retrofit = APIClient.getClient();
        cartService = retrofit.create(CartService.class);

        fetchCartData();
    }

    private void fetchCartData() {
        Call<List<Cart>> call = cartService.getCartForCustomer(loginResponse.getUserInfo().getUsersID());
        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (response.isSuccessful()) {
                    List<Cart> cartList = response.body();
                    Log.d("CartResponse", cartList != null ? cartList.toString() : "No response body");
                    cartAdapter = new CartAdapter(cartList, CartActivity.this);
                    rvItemCart.setAdapter(cartAdapter);
                } else {
                    Toast.makeText(CartActivity.this, "Failed to load cart", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {
                Toast.makeText(CartActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        tvHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onDeleteClick(int cartId, int position) {
        Call<Void> call = cartService.deleteCartItem(cartId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    cartAdapter.removeItem(position);
                    Toast.makeText(CartActivity.this, "Item removed from cart", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CartActivity.this, "Failed to remove item from cart", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(CartActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
