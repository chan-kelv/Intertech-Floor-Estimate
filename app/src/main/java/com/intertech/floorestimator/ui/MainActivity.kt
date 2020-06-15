package com.intertech.floorestimator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.flatdialoglibrary.dialog.FlatDialog
import com.intertech.floorestimator.MainViewModel
import com.intertech.floorestimator.R
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

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
        mainActivityVm?.getIsAdminUser()?.observe(this, Observer {
            text_main_logout.visibility = if (it) View.VISIBLE else View.GONE
        })

        text_main_userType.setOnClickListener { adminLogin() }
        text_main_logout.setOnClickListener { mainActivityVm?.adminLogout() }
    }

    private fun adminLogin() {
        val flatDialog = FlatDialog(this@MainActivity)
        flatDialog.setTitle("Admin Login")
//            .setSubtitle("write your profile info here")
            .setFirstTextFieldHint("Email")
            .setFirstTextField(DEFAULT_ADMIN_EMAIL)
            .setSecondTextFieldHint("Password")
            .setSecondTextField(DEFAULT_ADMIN_PASSWORD)
            .setFirstButtonText("Login")
            .setSecondButtonText("CANCEL")
            .withFirstButtonListner {
                Timber.d("Login")
                mainActivityVm?.attemptAdminLogin(flatDialog.firstTextField, flatDialog.secondTextField)
            }
            .withSecondButtonListner {
                flatDialog.dismiss()
            }
            .isCancelable(true)
            .show()
    }

    override fun onStart() {
        super.onStart()

        checkAdminLogin()
    }

    private fun checkAdminLogin() {
        mainActivityVm?.updateAdminAuthState()
    }

    companion object {
        val DEFAULT_ADMIN_EMAIL = "admin@admin.com"
        val DEFAULT_ADMIN_PASSWORD = "123456"
    }
}
