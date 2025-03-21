//22110410 - Huỳnh Thị Mỹ Tâm
package com.example.cosmesticapp.activity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.cosmesticapp.R;

import java.util.Random;

public class ForgetPassActivity extends AppCompatActivity {

    Button buttonGui;
    EditText editText;
    ImageButton imageButton;
    Button btnResend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forget_pass);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imageButton = (ImageButton) findViewById(R.id.imageBtnBack);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgetPassActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonGui = (Button) findViewById(R.id.btnGui);
        editText = (EditText) findViewById(R.id.editTextEmailAddress);

        buttonGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editText.getText().toString().trim();

                if (email.isEmpty()) {
                    Toast.makeText(ForgetPassActivity.this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(email)) {
                    Toast.makeText(ForgetPassActivity.this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
                } else {
                    // Gửi OTP đến email
                    sendOtpToEmail(email);
                }
            }
        });

    }

    private void sendOtpToEmail(String email) {
        // Tạo mã OTP ngẫu nhiên (6 chữ số)
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // OTP từ 100000 đến 999999

        // Lưu OTP vào SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("OTP_PREF", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("OTP", otp);
        editor.apply();

        // Gửi OTP đến email (giả lập)
        Toast.makeText(this, "Mã OTP đã gửi đến email: " + email, Toast.LENGTH_SHORT).show();

        // Hiển thị Dialog nhập OTP
        showOtpDialog();
    }

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void showOtpDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_otp_verification);
        dialog.setCancelable(true);

        EditText etOtp = dialog.findViewById(R.id.otpEditText);
        Button btnSubmitOtp = dialog.findViewById(R.id.verifyButton);

        btnSubmitOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otpInput = etOtp.getText().toString().trim();

                if (otpInput.isEmpty()) {
                    Toast.makeText(ForgetPassActivity.this, "Vui lòng nhập OTP", Toast.LENGTH_SHORT).show();
                } else {
                    // Lấy OTP đã lưu từ SharedPreferences
                    SharedPreferences sharedPreferences = getSharedPreferences("OTP_PREF", MODE_PRIVATE);
                    int savedOtp = sharedPreferences.getInt("OTP", 0);

                    if (otpInput.equals(String.valueOf(savedOtp))) {
                        Toast.makeText(ForgetPassActivity.this, "Xác thực thành công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                        // Chuyển đến màn hình đặt lại mật khẩu
                        Intent intent = new Intent(ForgetPassActivity.this, ForgetPassActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ForgetPassActivity.this, "Mã OTP không đúng", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        dialog.show();
    }
}