package com.intertech.floorestimator.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseVM : ViewModel() {
    protected val toastMessage: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    fun getToastMessage(): LiveData<String> = toastMessage
    fun clearToastMessage() {
        toastMessage.value = ""
    }

    abstract class BaseViewModelFactory : ViewModelProvider.Factory
//    class BaseViewModelFactory() : ViewModelProvider.Factory {
//        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//            return BaseVM() as T
//        }
//    }
}