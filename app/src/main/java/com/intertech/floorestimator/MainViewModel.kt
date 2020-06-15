package com.intertech.floorestimator

import android.text.SpannableString
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.intertech.floorestimator.ui.BaseVM
import com.intertech.floorestimator.ui.MainRepository
import com.intertech.floorestimator.util.TextResUtil
import timber.log.Timber

class MainViewModel(private val mainRepo: MainRepository): BaseVM() {
    private var fbAuth: FirebaseAuth

    private val userTypeText: MutableLiveData<SpannableString> by lazy { MutableLiveData<SpannableString>() }
    fun getUserTypeText(): LiveData<SpannableString> = userTypeText

    private val isAdminUser: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    fun getIsAdminUser(): LiveData<Boolean> = isAdminUser

    init {
        val defaultUserType = TextResUtil.getStringFromRes(R.string.main_userType_general)
        userTypeText.value = TextResUtil.createUnderlineText(defaultUserType)

        isAdminUser.value = false

        fbAuth = FirebaseAuth.getInstance()
        fbAuth.addAuthStateListener { authStatus ->
            updateAdminAuthState()
        }
    }

    fun updateAdminAuthState() {
        var userString = TextResUtil.getStringFromRes(R.string.main_userType_general)
        var isAdmin = false

        if (fbAuth.currentUser != null) {
            userString = TextResUtil.getStringFromRes(R.string.main_userType_admin)
            isAdmin = true
        }

        userTypeText.value = TextResUtil.createUnderlineText(userString)
        isAdminUser.value = isAdmin
    }

    fun attemptAdminLogin(email: String, password: String) {
        fbAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val adminUserString = TextResUtil.getStringFromRes(R.string.main_userType_admin)
                userTypeText.value = TextResUtil.createUnderlineText(adminUserString)
                toastMessage.value = "Login Success!"
            } else {
                Timber.e(task.exception)
                toastMessage.value = task.exception?.message ?: "Could not log into Admin"
            }
        }
    }

    fun adminLogout() {
        fbAuth.signOut()
        toastMessage.value = "Logged Out!"
    }

    class MainViewModelFactory(private val mainRepo: MainRepository): BaseViewModelFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(mainRepo) as T
        }
    }
}