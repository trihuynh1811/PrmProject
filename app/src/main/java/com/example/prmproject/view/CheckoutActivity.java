package com.example.prmproject.view;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmproject.R;
import com.example.prmproject.adapter.CartAdapter;
import com.example.prmproject.adapter.CheckoutAdapter;
import com.example.prmproject.api.APIClient;
import com.example.prmproject.dto.CreateOrderRequest;
import com.example.prmproject.dto.LoginResponse;
import com.example.prmproject.dto.OrderProductRequest;
import com.example.prmproject.dto.OrderResponse;
import com.example.prmproject.models.Cart;
import com.example.prmproject.service.CartService;
import com.example.prmproject.service.OrderService;
import com.example.prmproject.zalopay.Api.CreateOrder;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import vn.zalopay.sdk.Environment;
import vn.zalopay.sdk.ZaloPayError;
import vn.zalopay.sdk.ZaloPaySDK;
import vn.zalopay.sdk.listeners.PayOrderListener;

public class CheckoutActivity extends AppCompatActivity implements CartAdapter.OnDeleteClickListener, CartAdapter.OnQuantityChangeListener {
    private RecyclerView rvCheckoutList;
    private CheckoutAdapter cartAdapter;
    private CartService cartService;
    private LoginResponse loginResponse;
    private TextView btnCheckout, tvTotal, btnzalopay;
    List<Cart> cartList = null;
    List<OrderProductRequest> orderProductRequestList = new ArrayList<>();
    int totalMoney = 0;
    private OrderService orderService;

    private static final int NOTIFICATION_ID = 0;
    private static final String NOTIFICATION_ID_STRING = "My Notifications";
    private NotificationManager mNotifyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_checkout);

        tvTotal = findViewById(R.id.tvTotal);
        btnzalopay = findViewById(R.id.btnzalopay);
        btnCheckout = findViewById(R.id.btnCheckout);
        rvCheckoutList = findViewById(R.id.rvCheckoutList);
        loginResponse = getIntent().getParcelableExtra("loginResponse");

        StrictMode.ThreadPolicy policy = new
                StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // ZaloPay SDK Init
        ZaloPaySDK.init(553, Environment.SANDBOX);

        if (loginResponse != null) {
            Log.d("Cart", "LoginResponse received: " + loginResponse.toString());
        } else {
            Log.e("Cart", "No LoginResponse received");
        }

        Retrofit retrofit = APIClient.getClient();
        cartService = retrofit.create(CartService.class);
        orderService = retrofit.create(OrderService.class);
        btnzalopay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateOrder orderApi = new CreateOrder();
                try {
                    JSONObject data = orderApi.createOrder(Integer.toString(totalMoney));
                    Log.d("Amount", tvTotal.getText().toString());
                    String code = data.getString("returncode");
                    Toast.makeText(getApplicationContext(), "return_code: " + code, Toast.LENGTH_LONG).show();
                    Log.d("error", data.toString());
                    if (code.equals("1")) {
                        String token = data.getString("zptranstoken");
                        ZaloPaySDK.getInstance().payOrder(CheckoutActivity.this, token, "demozpdk://app", new PayOrderListener() {
                            @Override
                            public void onPaymentSucceeded(String s, String s1, String s2) {
                                sendNotification("Thông báo", "Giao dịch thành công!");
                                Log.d("cart list", cartList.toString());
                                CreateNewOrder();
                            }

                            @Override
                            public void onPaymentCanceled(String s, String s1) {
                                sendNotification("Thông báo", "Giao dịch đã được hủy!");
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }

                            @Override
                            public void onPaymentError(ZaloPayError zaloPayError, String s, String s1) {
                                sendNotification("Thông báo", "Giao dịch thất bại!");
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        fetchCartData();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ZaloPaySDK.getInstance().onResult(intent);
    }

    private void fetchCartData() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", 0);
        Call<List<Cart>> call = cartService.getCartForCustomer(userId);
        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (response.isSuccessful()) {
                    DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                    symbols.setGroupingSeparator('.');
                    DecimalFormat decimalFormat = new DecimalFormat("#,###", symbols);
                    cartList = response.body();
                    Log.d("CartResponse", cartList != null ? cartList.toString() : "No response body");
                    cartAdapter = new CheckoutAdapter(cartList);
                    rvCheckoutList.setAdapter(cartAdapter);
                    rvCheckoutList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    String formattedNumber = decimalFormat.format(CalculateTotalPrice(cartList)) + "đ";
                    tvTotal.setText(formattedNumber);
                } else {
                    Toast.makeText(CheckoutActivity.this, "Failed to load cart", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {
                Toast.makeText(CheckoutActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onDeleteClick(int cartId, int position) {

    }

    @Override
    public void onQuantityChange(int cartId, int position, boolean isIncrement) {

    }

    int CalculateTotalPrice(List<Cart> cartList) {
        int total = 0;
        for (int i = 0; i < cartList.size(); i++) {
            total += cartList.get(i).getProductCartDTO().getPrice() * cartList.get(i).getQuantity();
        }
        totalMoney = total;
        return total;
    }

    private void sendNotification(String title, String content) {
        mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Test notification channel";
            String description = "Test notification";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_ID_STRING, name, importance);
            channel.setDescription(description);
            mNotifyManager = getSystemService(NotificationManager.class);
            mNotifyManager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(this, NotificationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_ID_STRING)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground))
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground))
                        .bigLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground))
                )
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setVibrate(new long[]{100, 1000, 200, 340})
                .setAutoCancel(false)
                .setTicker("Notification");
        builder.setContentIntent(pendingIntent);

        Notification myNotification = builder.build();
        NotificationManagerCompat m = NotificationManagerCompat.from(getApplicationContext());
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            m.notify(new Random().nextInt(), myNotification);
        } else {
            Toast.makeText(getApplicationContext(), "please allow post notification in android manifest", Toast.LENGTH_LONG).show();
        }

    }

    private void CreateNewOrder() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", 0);
        String phone = sharedPreferences.getString("phone", "");
        String email = sharedPreferences.getString("email", "");
        String accountName = sharedPreferences.getString("name", "");
        Log.d("1", "1");
        Log.d("info", userId + " " + phone + " " + email + " " + accountName);
        for (Cart item : cartList) {
            OrderProductRequest orderProductRequest = new OrderProductRequest(item.getId(), item.getQuantity());
            orderProductRequestList.add(orderProductRequest);
        }
        Log.d("2", "2");
        CreateOrderRequest createOrderRequest = new CreateOrderRequest(
                totalMoney,
                userId,
                phone,
                email,
                accountName,
                orderProductRequestList
        );
        Log.d("3", "3");
        Call<OrderResponse> call = orderService.createOrder(createOrderRequest);
        Log.d("4", "4");

        call.enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                OrderResponse orderResponse = response.body();
                if (response.isSuccessful()) {
                    Log.d("5", "5");
                    Log.d("Order response", orderResponse != null ? orderResponse.toString() : "error create order fail no response");
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                Log.d("5.5", "5.5");
                Log.e("error", orderResponse.getStatus());
            }

            @Override
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                Log.d("6", "6");
                Log.e("LoginError", t.getMessage());
            }
        });
    }

}