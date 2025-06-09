package com.example.bank;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bank.utils.SecurityUtils;
import com.google.android.material.textfield.TextInputEditText;


public class LoginActivity extends AppCompatActivity {

    private TextInputEditText usernameInput;
    private TextInputEditText passwordInput;
    private Button loginButton;
    private TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Kiểm tra và hiển thị cảnh báo accessibility ngay khi vào màn hình đăng nhập
        SecurityUtils.checkAndShowAccessibilityWarning(this);

        // Khởi tạo các view
        usernameInput = findViewById(R.id.username_input);
        passwordInput = findViewById(R.id.password_input);
        loginButton = findViewById(R.id.login_button);
        forgotPassword = findViewById(R.id.forgot_password);

        // Thiết lập sự kiện cho nút đăng nhập
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        // Thiết lập sự kiện cho quên mật khẩu
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý quên mật khẩu
                Toast.makeText(LoginActivity.this, "Chức năng quên mật khẩu đang được phát triển", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Kiểm tra lại khi user quay về màn hình đăng nhập
        SecurityUtils.checkAndShowAccessibilityWarning(this);
    }

    /**
     * Xử lý đăng nhập
     */
    private void attemptLogin() {
        // Lấy thông tin đăng nhập
        String username = usernameInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

        // Kiểm tra thông tin đăng nhập
        if (TextUtils.isEmpty(username)) {
            usernameInput.setError("Vui lòng nhập tài khoản");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            passwordInput.setError("Vui lòng nhập mật khẩu");
            return;
        }

        // Trong thực tế, bạn sẽ gọi API để xác thực đăng nhập
        // Ở đây chúng ta chỉ giả lập đăng nhập thành công
        if (username.equals("admin") && password.equals("123456")) {
            // Đăng nhập thành công, chuyển đến màn hình chuyển tiền
            Intent intent = new Intent(LoginActivity.this, TransferActivity.class);
            startActivity(intent);
            finish(); // Đóng màn hình đăng nhập
        } else {
            // Đăng nhập thất bại
            Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
        }
    }
} 