package com.example.prmproject.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prmproject.R;
import com.example.prmproject.api.APIClient;
import com.example.prmproject.dto.AddToCartRequest;
import com.example.prmproject.dto.CartResponse;
import com.example.prmproject.models.Product;
import com.example.prmproject.service.CartService;
import com.example.prmproject.service.ProductService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {
    private TextView productName, productDescription, productPrice, cartBadge;
    private ImageView backButton, productImage;
    private Button addToCartButton;
    private ImageButton profileButton, homeButton, walletButton, cartButton;
    private int productId;
    private CartService cartService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        productName = findViewById(R.id.product_name);
        productDescription = findViewById(R.id.product_description);
        productPrice = findViewById(R.id.product_price);
        productImage = findViewById(R.id.product_image);
        addToCartButton = findViewById(R.id.add_to_cart_button);
        backButton = findViewById(R.id.back_button);
        profileButton = findViewById(R.id.profile);
        homeButton = findViewById(R.id.homePage);
        walletButton = findViewById(R.id.wallet);
        cartButton = findViewById(R.id.btnCart);
        cartBadge = findViewById(R.id.cartBadge);

        productId = getIntent().getIntExtra("productId", -1);
        if (productId != -1) {
            fetchProductDetails(productId);
        }

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCart(productId);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, User_Page_Activity.class);
                startActivity(intent);
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        walletButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement your wallet functionality here
            }
        });

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        cartService = APIClient.getClient().create(CartService.class);
        fetchCartQuantity();
    }

    private void fetchProductDetails(int productId) {
        ProductService productService = APIClient.getClient().create(ProductService.class);
        Call<Product> call = productService.getProductById(productId);

        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Product product = response.body();
                    productName.setText(product.getProductName());
                    productDescription.setText(product.getDescription());
                    productPrice.setText(String.valueOf(product.getPrice()) + " VNĐ");
                    Picasso.get().load(product.getProductImages().get(0).getImageUrl()).into(productImage);
                } else {
                    Toast.makeText(ProductDetailActivity.this, "Failed to fetch product details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(ProductDetailActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addToCart(int productId) {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", 0);

        AddToCartRequest addToCartRequest = new AddToCartRequest(userId, productId);
        Call<CartResponse> call = cartService.addToCart(addToCartRequest);

        call.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(ProductDetailActivity.this, "Added to cart", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProductDetailActivity.this, "Failed to add to cart", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                Toast.makeText(ProductDetailActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
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
                    Log.e("ProductDetailActivity", "Failed to fetch cart quantity: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.e("ProductDetailActivity", "API call failed: " + t.getMessage());
                Log.e("Quantity", "API call failed", t);
            }
        });
    }
}
