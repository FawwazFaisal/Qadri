package com.example.qadri.mvvm.model.otp

data class OtpModel(
    var login_id: String? = null,
    var otp_code: String? = null,
    val device_id: String
)