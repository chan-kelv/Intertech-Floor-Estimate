package com.intertech.floorestimator.ui

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(): AppCompatActivity() {
    fun showToast(message: String, length: Int = Toast.LENGTH_LONG) {
        Toast.makeText(this, message, length).show()
    }
}