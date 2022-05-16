package com.example.qadri.mvvm.model.customer

data class AccountReceivable(
    val `1_25_days`: String,
    val `26_45_days`: String,
    val `46_60_days`: String,
    val `61_90_days`: String,
    val more_than_90_days: String,
    val over_due: String,
    val receivable_id: String,
    val total: String
)