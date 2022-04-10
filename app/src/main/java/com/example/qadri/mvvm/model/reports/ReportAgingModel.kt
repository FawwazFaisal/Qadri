package com.example.qadri.mvvm.model.reports


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class ReportAgingModel(
    @SerializedName("name")
    @Expose
    val name: String? = "",
    @SerializedName("overdue")
    @Expose
    val overdue: String? = "",
    @SerializedName("total")
    @Expose
    val total: String? = ""
)