package com.example.prmproject.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prmproject.R;
import com.example.prmproject.adapter.ProductAdapter;
import com.example.prmproject.api.APIClient;
import com.example.prmproject.dto.AddToCartRequest;
import com.example.prmproject.dto.CartResponse;
import com.example.prmproject.dto.LoginResponse;
import com.example.prmproject.models.Category;
import com.example.prmproject.models.Product;
import com.example.prmproject.service.CartService;
import com.example.prmproject.service.CategoryService;
import com.example.prmproject.service.ProductService;
import com.example.prmproject.utils.RecyclerItemClickListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ProductAdapter.OnAddToCartClickListener {

    ImageButton profile, btnCart;
    Spinner spinnerCategory;
    CategoryService categoryService;
    ProductService productService;
    CartService cartService;
    private RecyclerView rvProductList;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private LoginResponse loginResponse;
    private TextView cartBadge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categoryService = APIClient.getClient().create(CategoryService.class);
        productService = APIClient.getClient().create(ProductService.class);
        cartService = APIClient.getClient().create(CartService.class);

        loginResponse = getIntent().getParcelableExtra("loginResponse");

        if (loginResponse != null) {
            Log.d("MainActivity", "LoginResponse received: " + loginResponse.toString());
        } else {
            Log.e("MainActivity", "No LoginResponse received");
        }

        spinnerCategory = findViewById(R.id.spinnerCategory);
        profile = findViewById(R.id.profile);
        btnCart = findViewById(R.id.btnCart);
        rvProductList = findViewById(R.id.rvProductList);
        cartBadge = findViewById(R.id.cartBadge);

        rvProductList.addOnItemTouchListener(new RecyclerItemClickListener(this, rvProductList, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Product selectedProduct = productList.get(position);
                Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
                intent.putExtra("productId", selectedProduct.getProductID());
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {
                // Do nothing
            }
        }));

        // Set up RecyclerView
        fetchProducts();

        // Set up click listeners
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                intent.putExtra("loginResponseCart", loginResponse);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, User_Page_Activity.class);
                intent.putExtra("loginResponseUser", loginResponse);
                startActivity(intent);
            }
        });

        fetchCategories();
        fetchCartQuantity();
    }

    private void fetchCategories() {
        Call<List<Category>> call = categoryService.getAllCategory();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Category> categories = response.body();
                    ArrayAdapter<Category> adapter = new ArrayAdapter<>(MainActivity.this,
                            android.R.layout.simple_spinner_item, categories);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCategory.setAdapter(adapter);
                } else {
                    Log.e("MainActivity", "Response not successful or body is null");
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e("MainActivity", "API call failed: " + t.getMessage());
            }
        });
    }

    private void fetchProducts() {
        Call<List<Product>> call = productService.getProducts();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    productList = response.body();
                    if (productList != null) {
                        SharedPreferences sharedPreferences = getSharedPreferences("ProductPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        for (Product product : productList) {
                            editor.putInt("productID_" + product.getProductID(), product.getProductID());
                        }
                        editor.apply();

                        productAdapter = new ProductAdapter(productList, MainActivity.this); // Pass MainActivity as listener
                        rvProductList.setAdapter(productAdapter);
                        rvProductList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    }
                } else {
                    Log.e("Product", "Response failed");
                    Log.e("Product", "Response failed with code: " + response.code());
                    Toast.makeText(MainActivity.this, "Failed to fetch products", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("Product", "API call failed", t);
                Toast.makeText(MainActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onAddToCartClick(int productID) {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", 0);

        AddToCartRequest addToCartRequest = new AddToCartRequest(userId, productID);

        Call<CartResponse> call = cartService.addToCart(addToCartRequest);
        call.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                if (response.isSuccessful()) {
                    CartResponse cartResponse = response.body();
                    if (cartResponse != null) {
                        Toast.makeText(MainActivity.this, cartResponse.getStatus(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("MainActivity", "Failed to add to cart: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                Log.e("MainActivity", "API call failed: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Failed to add to cart", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void fetchCartQuantity() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", 0);
        Call<Integer> call = cartService.getQuantityInCart(userId);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful() && response.body() != null) {
                    int quantity = response.body();
                    Log.d("QuantityResponse", "Quantity :" + quantity);
                    if (quantity > 0) {
                        cartBadge.setVisibility(View.VISIBLE);
                        cartBadge.setText(String.valueOf(quantity));
                    } else {
                        cartBadge.setVisibility(View.GONE);
                    }
                } else {
                    Log.e("MainActivity", "Failed to fetch cart quantity: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e("MainActivity", "API call failed: " + t.getMessage());
                Log.e("Quantity", "API call failed", t);
            }
        });
    }
}
