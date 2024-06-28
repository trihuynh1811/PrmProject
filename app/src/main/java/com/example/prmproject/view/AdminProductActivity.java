package com.example.prmproject.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prmproject.R;
import com.example.prmproject.adapter.ProductAdapter;
import com.example.prmproject.adapter.model.Product;

import java.util.ArrayList;
import java.util.List;
public class AdminProductActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<Product> productList;
    private EditText searchBar;
    private Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product_management);

        recyclerView = findViewById(R.id.recyclerView);
        searchBar = findViewById(R.id.searchBar);
        buttonAdd = findViewById(R.id.buttonAdd);

        // Initialize product list
        productList = new ArrayList<>();
        productList.add(new Product("Product 1", 100.0, "Category 1", 10));
        productList.add(new Product("Product 2", 200.0, "Category 2", 20));
        // Add more products as needed

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productAdapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(productAdapter);

        productAdapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onEditClick(int position) {
                // Handle edit product
            }

            @Override
            public void onDeleteClick(int position) {
                // Handle delete product
                productList.remove(position);
                productAdapter.notifyItemRemoved(position);
            }
        });

        // Add product button click listener
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle add product
            }
        });

        // Search bar text change listener
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Do nothing
            }
        });
    }

    private void filter(String text) {
        List<Product> filteredList = new ArrayList<>();
        for (Product product : productList) {
            if (product.getName().toLowerCase().contains(text.toLowerCase()) ||
                    product.getCategory().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(product);
            }
        }
        productAdapter.filterList(filteredList);
    }
}
