package com.example.qadri.mvvm.model.notification


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class NotificationModel(
    @SerializedName("message")
    @Expose
    val message: String? = "",
    @SerializedName("date")
    @Expose
    val date: String? = "",
    @SerializedName("time")
    @Expose
    val time: String? = ""
)