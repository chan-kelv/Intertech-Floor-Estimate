package com.intertech.floorestimator

import android.text.SpannableString
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.intertech.floorestimator.ui.MainRepository
import com.intertech.floorestimator.util.TextResUtil

class MainViewModel(private val mainRepo: MainRepository): ViewModel() {
    private val userTypeText: MutableLiveData<SpannableString> by lazy { MutableLiveData<SpannableString>() }
    fun getUserTypeText(): LiveData<SpannableString> = userTypeText

    private val userTypeFetchingAuth: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    fun getUserTypeFetchingAuth(): LiveData<Boolean> = userTypeFetchingAuth

    init {
        val defaultUserType = TextResUtil.getStringFromRes(R.string.main_userType_default)
        userTypeText.value = TextResUtil.createUnderlineText(defaultUserType)

        userTypeFetchingAuth.value = false
    }
    class MainViewModelFactory(private val mainRepo: MainRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(mainRepo) as T
        }
    }
}