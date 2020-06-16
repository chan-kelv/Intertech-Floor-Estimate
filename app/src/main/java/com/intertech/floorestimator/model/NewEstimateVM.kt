package com.intertech.floorestimator.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.intertech.floorestimator.ui.BaseVM

class NewEstimateVM : BaseVM() {
    private val customerInfo: MutableLiveData<CustomerInfo> by lazy { MutableLiveData<CustomerInfo>() }
    fun getCustomerInfo(): LiveData<CustomerInfo> = customerInfo
    fun setCustomerInfo(cInfo: CustomerInfo) {
        customerInfo.value = cInfo
    }

    class NewEstimateVmFactory(): BaseViewModelFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return NewEstimateVM() as T
        }
    }
}