package com.example.cosmesticapp.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cosmesticapp.R;
import com.example.cosmesticapp.dto.LoginRequest;
import com.example.cosmesticapp.dto.LoginResponse;
import com.example.cosmesticapp.service.RetrofitClient;
import com.example.cosmesticapp.share.SharedPrefManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextView registerText;
    TextView forgetText;
    private EditText loginEditText, passwordEditText;
    private ImageButton loginButton;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // 22110410 - Huỳnh Thị Mỹ Tâm
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        registerText = findViewById(R.id.registerText);
        registerText.setOnClickListener(v -> { // 22110394 - Ong Vĩnh Phát
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });

        forgetText = (TextView) findViewById(R.id.forgetpassText);
        forgetText.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ForgetPassActivity.class);
            startActivity(intent);
            finish();
        });

        // 22110394 - Ong Vĩnh Phát

        // Check if user is already logged in
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            // User is already logged in, redirect to main activity
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        // Initialize UI components
        loginEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.imageButtonLogin);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);

        // Set click listener for login button
        loginButton.setOnClickListener(v -> {
            if (validateInputs()) {
                loginUser();
            }
        });

    }

    // 22110394 - Ong Vĩnh Phát
    private boolean validateInputs() {
        String login = loginEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Validate login (username or email)
        if (login.isEmpty()) {
            loginEditText.setError("Username or email is required");
            loginEditText.requestFocus();
            return false;
        }

        // Validate password
        if (password.isEmpty()) {
            passwordEditText.setError("Password is required");
            passwordEditText.requestFocus();
            return false;
        }

        return true;
    }

    // 22110394 - Ong Vĩnh Phát
    private void loginUser() {
        // Show progress bar
        progressBar.setVisibility(View.VISIBLE);

        // Get input values
        String login = loginEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Create login request
        LoginRequest loginRequest = new LoginRequest(login, password);

        // Call API
        RetrofitClient.getInstance().getApi().loginUser(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();

                    if (loginResponse.isSuccess() && loginResponse.getUser() != null) {
                        // Save user to SharedPreferences
                        SharedPrefManager.getInstance(LoginActivity.this).saveUser(loginResponse.getUser());

                        // Show success message
                        Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();

                        // Navigate to main activity
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    } else {
                        // Show error message
                        Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    // Handle error response
                    try {
                        String errorBody = response.errorBody().string();
                        Toast.makeText(LoginActivity.this, "Login failed: " + errorBody, Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        Toast.makeText(LoginActivity.this, "Login failed. Please try again.", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}