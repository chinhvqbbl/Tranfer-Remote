package com.example.bank.utils

import android.accessibilityservice.AccessibilityServiceInfo
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.view.accessibility.AccessibilityManager
import com.example.bank.R

/**
 * Utility để kiểm tra và cảnh báo khi có app sử dụng quyền Accessibility
 */
class SecurityUtils {
    
    companion object {
    
        /**
         * Kiểm tra có app nào đang sử dụng quyền Accessibility không
         * @param context Application context
         * @return true nếu có app đang sử dụng quyền Accessibility
         */
        @JvmStatic
        fun isAccessibilityServiceEnabled(context: Context): Boolean {
            val accessibilityManager = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
            val enabledServices = accessibilityManager.getEnabledAccessibilityServiceList(AccessibilityServiceInfo.FEEDBACK_ALL_MASK)
            return enabledServices.isNotEmpty()
        }
        
        /**
         * Hiển thị cảnh báo khi phát hiện app đang sử dụng quyền Accessibility
         * @param context Activity context
         */
        @JvmStatic
        fun showAccessibilityWarningDialog(context: Context) {
            AlertDialog.Builder(context)
                .setTitle("⚠️ Cảnh báo bảo mật")
                .setMessage("Phát hiện có ứng dụng đang sử dụng quyền trợ năng (Accessibility).\n\nĐiều này có thể cho phép ứng dụng khác đọc thông tin trên màn hình và thực hiện các thao tác tự động.\n\nVui lòng kiểm tra và tắt các dịch vụ trợ năng không cần thiết để bảo vệ thông tin tài khoản của bạn.")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Mở cài đặt") { dialog, _ ->
                    dialog.dismiss()
                    openAccessibilitySettings(context)
                }
                .setNegativeButton("Đã hiểu") { dialog, _ ->
                    dialog.dismiss()
                }
                .setCancelable(true)
                .show()
        }
        
        /**
         * Mở trang cài đặt Accessibility
         * @param context Application context
         */
        @JvmStatic
        private fun openAccessibilitySettings(context: Context) {
            try {
                val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            } catch (e: Exception) {
                // Fallback nếu không mở được cài đặt Accessibility
                try {
                    val intent = Intent(Settings.ACTION_SETTINGS)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    context.startActivity(intent)
                } catch (ex: Exception) {
                    // Do nothing if can't open settings
                }
            }
        }
    
        /**
         * Kiểm tra và hiển thị cảnh báo nếu có app đang sử dụng quyền Accessibility
         * @param context Activity context
         */
        @JvmStatic
        fun checkAndShowAccessibilityWarning(context: Context) {
            if (isAccessibilityServiceEnabled(context)) {
                showAccessibilityWarningDialog(context)
            }
        }
    }
}