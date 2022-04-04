package com.example.qadri.mvvm.model.login

data class LoginResponse(
    var token: String? = null,
    var two_factor: String? = null
)