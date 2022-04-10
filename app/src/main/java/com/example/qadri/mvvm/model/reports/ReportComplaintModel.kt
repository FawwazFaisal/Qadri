package com.example.qadri.mvvm.model.reports


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class ReportComplaintModel(
    @SerializedName("name")
    @Expose
    val name: String? = "",
    @SerializedName("phone")
    @Expose
    val phone: String? = "",
    @SerializedName("type")
    @Expose
    val type: String? = "",
    @SerializedName("date")
    @Expose
    val date: String? = ""
)