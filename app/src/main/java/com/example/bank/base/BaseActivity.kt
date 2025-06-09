package com.example.bank.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.bank.utils.SecurityUtils

/**
 * Base activity class that all activities should extend
 * Provides common functionality including security checks
 */
abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var binding: VB
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Setup data binding
        binding = DataBindingUtil.setContentView(this, getLayoutActivity())
        binding.lifecycleOwner = this
        
        // Perform security check on activity start
        performSecurityCheck()
        
        // Initialize views and setup
        initViews()
        onResizeViews()
        onClickViews()
        observerData()
    }
    
    override fun onResume() {
        super.onResume()
        // Additional security check when returning to app
        performSecurityCheck()
    }
    
    /**
     * Kiểm tra và hiển thị cảnh báo nếu có app đang sử dụng quyền Accessibility
     */
    protected open fun performSecurityCheck() {
        SecurityUtils.checkAndShowAccessibilityWarning(this)
    }
    
    /**
     * Get layout resource ID for the activity
     * @return Layout resource ID
     */
    abstract fun getLayoutActivity(): Int
    
    /**
     * Initialize UI components
     * Called after data binding is set up
     */
    abstract fun initViews()
    
    /**
     * Handle view resizing for different screen sizes
     * Called after initViews()
     */
    abstract fun onResizeViews()
    
    /**
     * Set up click listeners for views
     * Called after onResizeViews()
     */
    abstract fun onClickViews()
    
    /**
     * Observe LiveData changes
     * Called after onClickViews()
     */
    abstract fun observerData()
}