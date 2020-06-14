package com.intertech.floorestimator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.intertech.floorestimator.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkAdminLogin()
    }

    private fun checkAdminLogin() {
        // set default first
        
    }
}
