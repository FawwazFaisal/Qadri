package com.example.qadri.mvvm.model.previousVisit


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class PreviousVisitModel(
    @SerializedName("visited_by")
    @Expose
    val visitedBy: String? = "",
    @SerializedName("visted_date")
    @Expose
    val vistedDate: String? = "",
    @SerializedName("visted_time")
    @Expose
    val vistedTime: String? = "",
    @SerializedName("address")
    @Expose
    val address: String? = ""
)