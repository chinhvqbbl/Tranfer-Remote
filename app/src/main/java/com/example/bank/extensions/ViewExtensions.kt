package com.example.bank.extensions

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import com.example.bank.R

/**
 * Extension functions for View components
 * Provides common view operations and utilities
 */

// Visibility control extensions
fun View.visibleView() {
    visibility = View.VISIBLE
}

fun View.goneView() {
    visibility = View.GONE
}

fun View.invisibleView() {
    visibility = View.INVISIBLE
}

// Click handling with debounce
private var lastClickTime = 0L
private const val CLICK_DEBOUNCE_TIME = 700L

fun View.click(action: () -> Unit) {
    setOnClickListener {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime > CLICK_DEBOUNCE_TIME) {
            lastClickTime = currentTime
            action.invoke()
        }
    }
}

// View resizing
fun View.resizeView(width: Int, height: Int) {
    val params = layoutParams
    params.width = width
    params.height = height
    layoutParams = params
}

// Animation extensions
fun View.visibleAnimation(context: Context) {
    if (visibility != View.VISIBLE) {
        val animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in)
        startAnimation(animation)
        visibleView()
    }
}

fun View.goneAnimation(context: Context) {
    if (visibility != View.GONE) {
        val animation = AnimationUtils.loadAnimation(context, android.R.anim.fade_out)
        startAnimation(animation)
        goneView()
    }
}

// Keyboard management
fun View.hideKeyboard() {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}