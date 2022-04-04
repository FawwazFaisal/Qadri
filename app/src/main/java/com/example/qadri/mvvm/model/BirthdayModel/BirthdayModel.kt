package com.example.qadri.mvvm.model.BirthdayModel


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BirthdayModel(
    @SerializedName("date")
    val date: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("phno")
    val phNo: String = ""
) : Serializable