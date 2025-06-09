package com.example.bank

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.bank.base.BaseActivity
import com.example.bank.databinding.ActivityMainBinding
import com.example.bank.extensions.click
import com.example.bank.utils.SecurityUtils

class MainActivity : BaseActivity<ActivityMainBinding>() {
    
    override fun getLayoutActivity(): Int = R.layout.activity_main
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    
    override fun initViews() {
        // Initialize UI components here
        
    }
    
    override fun onResizeViews() {
        // Handle view resizing for different screen sizes
        
    }
    
    override fun onClickViews() {
        // Set up click listeners
        binding.buttonSecurityCheck.click { performManualSecurityCheck() }
    }
    
    override fun observerData() {
        // Observe LiveData changes
        
    }
    
    /**
     * Kiểm tra thủ công (gọi từ button click)
     */
    private fun performManualSecurityCheck() {
        SecurityUtils.checkAndShowAccessibilityWarning(this)
    }
}