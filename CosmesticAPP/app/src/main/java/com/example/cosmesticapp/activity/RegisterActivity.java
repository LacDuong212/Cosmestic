package com.example.cosmesticapp.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cosmesticapp.R;
import com.example.cosmesticapp.dto.ApiResponse;
import com.example.cosmesticapp.dto.RegisterRequest;
import com.example.cosmesticapp.dto.VerifyOTPRequest;
import com.example.cosmesticapp.service.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText nameEditText, emailEditText, passwordEditText, confirmPwdEditText;
    private RadioButton maleRadioButton, femaleRadioButton;
    private ImageButton registerButton;
    private TextView loginTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        // Initialize UI components
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPwdEditText = findViewById(R.id.confirmPwdEditText);
        maleRadioButton = findViewById(R.id.maleRadioButton);
        femaleRadioButton = findViewById(R.id.FemaleRadioButton);
        registerButton = findViewById(R.id.imageButton1);
        loginTextView = findViewById(R.id.registerText);

//        // Adding RadioGroup programmatically since it's missing in the layout
//        RadioGroup genderRadioGroup = new RadioGroup(this);
//        genderRadioGroup.addView(maleRadioButton);
//        genderRadioGroup.addView(femaleRadioButton);

        // Set click listener for register button
        registerButton.setOnClickListener(v -> {
            if (validateInputs()) {
                registerUser();
            }
        });

        // Set click listener for login text
        loginTextView.setOnClickListener(v -> {
            // Navigate back to login activity
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });
    }

    private boolean validateInputs() {
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String confirmPassword = confirmPwdEditText.getText().toString().trim();

        // Validate name
        if (name.isEmpty()) {
            nameEditText.setError("Name is required");
            nameEditText.requestFocus();
            return false;
        }

        // Validate email
        if (email.isEmpty()) {
            emailEditText.setError("Email is required");
            emailEditText.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Please enter a valid email");
            emailEditText.requestFocus();
            return false;
        }

        // Validate password
        if (password.isEmpty()) {
            passwordEditText.setError("Password is required");
            passwordEditText.requestFocus();
            return false;
        }

        if (password.length() < 6) {
            passwordEditText.setError("Password should be at least 6 characters long");
            passwordEditText.requestFocus();
            return false;
        }

        // Validate confirm password
        if (confirmPassword.isEmpty()) {
            confirmPwdEditText.setError("Please confirm your password");
            confirmPwdEditText.requestFocus();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            confirmPwdEditText.setError("Passwords don't match");
            confirmPwdEditText.requestFocus();
            return false;
        }

        // Validate gender selection
        if (!maleRadioButton.isChecked() && !femaleRadioButton.isChecked()) {
            Toast.makeText(this, "Please select your gender", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void registerUser() {
        // Show progress dialog
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        // Get input values
        String username = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String gender = maleRadioButton.isChecked() ? "MALE" : "FEMALE";

        // Create register request
        RegisterRequest registerRequest = new RegisterRequest(username, password, email, gender);

        // Call API
        RetrofitClient.getInstance().getApi().registerUser(registerRequest).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                progressDialog.dismiss();

                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();

                    if (apiResponse.isSuccess()) {
                        // Show OTP verification dialog
                        showOTPVerificationDialog(email);
                    } else {
                        // Show error message
                        Toast.makeText(RegisterActivity.this, apiResponse.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    // Handle error response
                    Toast.makeText(RegisterActivity.this, "Registration failed. Please try again.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showOTPVerificationDialog(String email) {
        // Create a dialog for OTP verification
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Email Verification");

        // Inflate a custom layout for the dialog
        View view = getLayoutInflater().inflate(R.layout.dialog_otp_verification, null);
        EditText otpEditText = view.findViewById(R.id.otpEditText);
        Button verifyButton = view.findViewById(R.id.verifyButton);
        Button resendButton = view.findViewById(R.id.resendButton);

        builder.setView(view);
        builder.setCancelable(false);

        AlertDialog dialog = builder.create();
        dialog.show();

        // Set click listener for verify button
        verifyButton.setOnClickListener(v -> {
            String otp = otpEditText.getText().toString().trim();

            if (otp.isEmpty()) {
                otpEditText.setError("Please enter OTP");
                otpEditText.requestFocus();
                return;
            }

            // Show progress
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Verifying OTP...");
            progressDialog.setCancelable(false);
            progressDialog.show();

            // Create OTP verification request
            VerifyOTPRequest verifyOTPRequest = new VerifyOTPRequest(email, otp);

            // Call API to verify OTP
            RetrofitClient.getInstance().getApi().verifyOTP(verifyOTPRequest).enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    progressDialog.dismiss();

                    if (response.isSuccessful() && response.body() != null) {
                        ApiResponse apiResponse = response.body();

                        if (apiResponse.isSuccess()) {
                            // Close dialog
                            dialog.dismiss();

                            // Show success message
                            Toast.makeText(RegisterActivity.this, apiResponse.getMessage(), Toast.LENGTH_LONG).show();

                            // Navigate to login activity
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                            finish();
                        } else {
                            // Show error message
                            Toast.makeText(RegisterActivity.this, apiResponse.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    } else {
                        // Handle error response
                        Toast.makeText(RegisterActivity.this, "OTP verification failed. Please try again.", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });

        // Set click listener for resend button
        resendButton.setOnClickListener(v -> {
            // Implement resend OTP functionality
            Toast.makeText(RegisterActivity.this, "Resending OTP...", Toast.LENGTH_SHORT).show();

            // Call the register API again to resend OTP
            RegisterRequest registerRequest = new RegisterRequest(
                    nameEditText.getText().toString().trim(),
                    passwordEditText.getText().toString().trim(),
                    email,
                    maleRadioButton.isChecked() ? "MALE" : "FEMALE"
            );

            RetrofitClient.getInstance().getApi().registerUser(registerRequest).enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        ApiResponse apiResponse = response.body();
                        Toast.makeText(RegisterActivity.this, apiResponse.getMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Failed to resend OTP. Please try again.", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    Toast.makeText(RegisterActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}