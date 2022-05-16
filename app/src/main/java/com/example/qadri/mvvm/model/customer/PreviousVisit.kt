package com.example.qadri.mvvm.model.customer

data class PreviousVisit(
    val customer_id: String,
    val first_name: String,
    val record_id: String,
    val title: String,
    val visit_type: String,
    val visited_time: String
)