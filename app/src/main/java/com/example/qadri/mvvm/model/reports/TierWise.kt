package com.example.qadri.mvvm.model.reports

data class TierWise(
    val `0-5000`: String = "",
    val `10000-25000`: String = "",
    val `100000-250000`: String = "",
    val `1000000-3000000`: String = "",
    val `25000-50000`: String = "",
    val `250000-500000`: String = "",
    val `5000-10000`: String = "",
    val `50000-100000`: String = "",
    val `500000-1000000`: String = "",
    val above_3000000: String = ""
)