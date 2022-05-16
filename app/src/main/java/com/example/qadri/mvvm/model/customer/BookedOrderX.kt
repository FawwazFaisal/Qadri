package com.example.qadri.mvvm.model.customer

data class BookedOrderX(
    val date: String,
    val discount_limit: String,
    val order_amount: String,
    val order_code: String,
    val order_detail: List<OrderDetailX>,
    val order_id: String,
    val po_number: String,
    val product_quantity: String,
    val time: String,
    val total_amount: String
)