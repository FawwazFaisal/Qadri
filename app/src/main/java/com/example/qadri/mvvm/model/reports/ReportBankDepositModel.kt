package com.example.qadri.mvvm.model.reports


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class ReportBankDepositModel(
    @SerializedName("name")
    @Expose
    val name: String? = "",
    @SerializedName("date")
    @Expose
    val date: String? = "",
    @SerializedName("amount")
    @Expose
    val amount: String? = ""
)