package com.intertech.floorestimator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.intertech.floorestimator.MainViewModel
import com.intertech.floorestimator.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var mainActivityVm: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityVm = ViewModelProvider(this, MainViewModel.MainViewModelFactory(MainRepository.getInstance()))
            .get(MainViewModel::class.java)
        mainActivityVm?.getUserTypeText()?.observe(this, Observer {
            text_main_userType.text = it
        })
    }

    override fun onStart() {
        super.onStart()

        checkAdminLogin()
    }

    private fun checkAdminLogin() {
        mainActivityVm?.checkAdminAuth()
    }
}
