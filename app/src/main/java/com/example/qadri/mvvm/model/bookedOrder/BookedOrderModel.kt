package com.example.qadri.mvvm.model.bookedOrder


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class BookedOrderModel(
    @SerializedName("order_id")
    @Expose
    val orderId: String? = "",
    @SerializedName("time")
    @Expose
    val time: String? = "",
    @SerializedName("date")
    @Expose
    val date: String? = "",
    @SerializedName("qty")
    @Expose
    val qty: String? = "",
    @SerializedName("amount")
    @Expose
    val amount: String? = "",
    @SerializedName("deposit")
    @Expose
    val deposit: String? = ""
)