package com.intertech.floorestimator

import android.text.SpannableString
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.intertech.floorestimator.ui.MainRepository
import com.intertech.floorestimator.util.TextResUtil

class MainViewModel(private val mainRepo: MainRepository): ViewModel() {
    private lateinit var fbAuth: FirebaseAuth

    private val userTypeText: MutableLiveData<SpannableString> by lazy { MutableLiveData<SpannableString>() }
    fun getUserTypeText(): LiveData<SpannableString> = userTypeText

    private val userTypeFetchingAuth: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    fun getUserTypeFetchingAuth(): LiveData<Boolean> = userTypeFetchingAuth

    init {
        val defaultUserType = TextResUtil.getStringFromRes(R.string.main_userType_general)
        userTypeText.value = TextResUtil.createUnderlineText(defaultUserType)

        userTypeFetchingAuth.value = false

        fbAuth = FirebaseAuth.getInstance()
    }

    fun checkAdminAuth() {
        userTypeText.value = if (fbAuth.currentUser == null) {
            val generalUserString = TextResUtil.getStringFromRes(R.string.main_userType_general)
            TextResUtil.createUnderlineText(generalUserString)
        } else {
            val adminUserString = TextResUtil.getStringFromRes(R.string.main_userType_admin)
            TextResUtil.createUnderlineText(adminUserString)
        }
    }

    class MainViewModelFactory(private val mainRepo: MainRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(mainRepo) as T
        }
    }
}