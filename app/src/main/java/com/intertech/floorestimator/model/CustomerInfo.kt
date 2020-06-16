package com.intertech.floorestimator.model

import android.util.Patterns
import android.util.Patterns.PHONE
import java.util.*
import java.util.regex.Pattern

data class CustomerInfo(
    var date: String?,
    var fullName: String?,
    var address: String?,
    var postalCode: String?,
    var phone1: PhoneNumber?,
    var phone2 : PhoneNumber?,
    var phone3: PhoneNumber?,
    var attentionName: String? = ""
) {
    data class PhoneNumber(
        var phoneNumber: String,
        var ext: String? = "",
        var phoneType: String? = "primary"
    ) {
        companion object {
            fun isValidPhone(number: String): Boolean {
                return Patterns.PHONE.matcher(number).matches()
            }
        }
    }

    companion object {
        fun createValidCustomerInfo(
            date: String?,
            fullName: String?,
            address: String?,
            postalCode: String?,
            phone1: PhoneNumber?,
            phone2: PhoneNumber?,
            phone3: PhoneNumber?,
            attentionName: String?
        ): CustomerInfo? {
            if (date.isNullOrBlank() || fullName.isNullOrBlank() || address.isNullOrBlank() ||
                postalCode.isNullOrBlank() || (phone1 == null && phone2 == null && phone3 == null)
            )
                return null
            else {
                return CustomerInfo(
                    date,
                    fullName,
                    address,
                    postalCode,
                    phone1,
                    phone2,
                    phone3,
                    attentionName
                )
            }
        }
    }
}