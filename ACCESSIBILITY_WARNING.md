# Cảnh báo Quyền Accessibility

## Tính năng

Ứng dụng sẽ tự động hiển thị cảnh báo khi phát hiện có app đang sử dụng quyền Accessibility.

## Cách hoạt động

1. **Tự động kiểm tra** khi mở app (trong BaseActivity)
2. **Hiển thị dialog cảnh báo** nếu phát hiện có app đang dùng quyền Accessibility
3. **Người dùng có thể**:
   - Bấm "Mở cài đặt" để vào trang Accessibility Settings
   - Bấm "Đã hiểu" để đóng dialog

## Files chính

1. **SecurityUtils.kt** - Chứa logic kiểm tra và hiển thị cảnh báo
2. **BaseActivity.kt** - Tự động gọi kiểm tra khi activity khởi động
3. **MainActivity.kt** - Có button để kiểm tra thủ công

## Sử dụng

### Tự động (đã tích hợp):
```kotlin
class YourActivity : BaseActivity<YourBinding>() {
    // Tự động kiểm tra khi activity start/resume
}
```

### Thủ công:
```kotlin
SecurityUtils.checkAndShowAccessibilityWarning(context)
```

## Thông báo hiển thị

"⚠️ Cảnh báo bảo mật

Phát hiện có ứng dụng đang sử dụng quyền trợ năng (Accessibility).

Điều này có thể cho phép ứng dụng khác đọc thông tin trên màn hình và thực hiện các thao tác tự động.

Vui lòng kiểm tra và tắt các dịch vụ trợ năng không cần thiết để bảo vệ thông tin tài khoản của bạn."