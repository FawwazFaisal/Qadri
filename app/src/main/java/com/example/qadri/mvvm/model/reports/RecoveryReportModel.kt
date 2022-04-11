package com.example.qadri.mvvm.model.reports


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import java.io.Serializable

data class RecoveryReportModel(
    @SerializedName("name")
    @Expose
    val name: String? = "",
    @SerializedName("phone")
    @Expose
    val phone: String? = "",
    @SerializedName("type")
    @Expose
    val type: String? = "",
    @SerializedName("amount")
    @Expose
    val amount: String? = ""
) : Serializable