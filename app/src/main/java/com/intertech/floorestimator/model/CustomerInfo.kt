package com.intertech.floorestimator.model

import java.util.*

data class CustomerInfo(
    var date: String?,
    var fullName: String?,
    var address: String?,
    var postalCode: String?,
    var primaryPhone: PhoneNumber?,
    var mobilePhone : PhoneNumber?,
    var otherPhone: PhoneNumber?,
    var attentionName: String?
) {
    data class PhoneNumber(
        var phoneNumber: String,
        var ext: String? = "",
        var phoneType: String? = "primary"
    )
}