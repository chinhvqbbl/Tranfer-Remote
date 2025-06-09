package com.example.bank;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bank.utils.SecurityUtils;
import com.google.android.material.textfield.TextInputEditText;


public class TransferActivity extends AppCompatActivity {

    private TextInputEditText recipientInput;
    private TextInputEditText amountInput;
    private TextInputEditText noteInput;
    private EditText otp1, otp2, otp3, otp4, otp5, otp6;
    private Button transferButton;
    private TextView resendOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        // Kiểm tra và hiển thị cảnh báo accessibility ở màn hình chuyển tiền
        SecurityUtils.checkAndShowAccessibilityWarning(this);

        // Khởi tạo các view
        recipientInput = findViewById(R.id.recipient_input);
        amountInput = findViewById(R.id.amount_input);
        noteInput = findViewById(R.id.note_input);
        otp1 = findViewById(R.id.otp_1);
        otp2 = findViewById(R.id.otp_2);
        otp3 = findViewById(R.id.otp_3);
        otp4 = findViewById(R.id.otp_4);
        otp5 = findViewById(R.id.otp_5);
        otp6 = findViewById(R.id.otp_6);
        transferButton = findViewById(R.id.transfer_button);
        resendOtp = findViewById(R.id.resend_otp);

        // Thiết lập sự kiện cho các ô OTP
        setupOtpFields();

        // Thiết lập sự kiện cho nút chuyển tiền
        transferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptTransfer();
            }
        });

        // Thiết lập sự kiện cho nút gửi lại OTP
        resendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Giả lập gửi lại OTP
                Toast.makeText(TransferActivity.this, "Đã gửi lại mã OTP", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Kiểm tra lại khi user quay về màn hình chuyển tiền
        SecurityUtils.checkAndShowAccessibilityWarning(this);
    }

    /**
     * Thiết lập sự kiện cho các ô OTP
     */
    private void setupOtpFields() {
        // Thiết lập sự kiện cho ô OTP 1
        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    otp2.requestFocus();
                }
            }
        });

        // Thiết lập sự kiện cho ô OTP 2
        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    otp3.requestFocus();
                }
            }
        });

        // Thiết lập sự kiện cho ô OTP 3
        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    otp4.requestFocus();
                }
            }
        });

        // Thiết lập sự kiện cho ô OTP 4
        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    otp5.requestFocus();
                }
            }
        });

        // Thiết lập sự kiện cho ô OTP 5
        otp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    otp6.requestFocus();
                }
            }
        });
    }




    /**
     * Xử lý chuyển tiền
     */
    private void attemptTransfer() {
        // Lấy thông tin chuyển tiền
        String recipient = recipientInput.getText().toString().trim();
        String amount = amountInput.getText().toString().trim();
        String note = noteInput.getText().toString().trim();
        String otp = otp1.getText().toString() + 
                    otp2.getText().toString() + 
                    otp3.getText().toString() + 
                    otp4.getText().toString() + 
                    otp5.getText().toString() + 
                    otp6.getText().toString();

        // Kiểm tra thông tin chuyển tiền
        if (recipient.isEmpty()) {
            recipientInput.setError("Vui lòng nhập số tài khoản người nhận");
            return;
        }

        if (amount.isEmpty()) {
            amountInput.setError("Vui lòng nhập số tiền");
            return;
        }

        if (otp.length() != 6) {
            Toast.makeText(TransferActivity.this, "Vui lòng nhập đầy đủ mã OTP", Toast.LENGTH_SHORT).show();
            return;
        }

        // Trong thực tế, bạn sẽ gọi API để thực hiện chuyển tiền
        // Ở đây chúng ta chỉ giả lập chuyển tiền thành công
        if (otp.equals("123456")) {
            // Chuyển tiền thành công
            Toast.makeText(TransferActivity.this, "Chuyển tiền thành công", Toast.LENGTH_SHORT).show();
            finish(); // Đóng màn hình chuyển tiền
        } else {
            // Chuyển tiền thất bại
            Toast.makeText(TransferActivity.this, "Mã OTP không đúng", Toast.LENGTH_SHORT).show();
        }
    }
} 