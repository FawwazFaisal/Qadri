package com.example.qadri.mvvm.model.BirthdayModel


import com.google.gson.annotations.SerializedName

data class BirthdayResponse(
    @SerializedName("template")
    val template: Data = Data()
)