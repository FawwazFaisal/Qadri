package com.example.qadri.mvvm.model.reports

import java.io.Serializable

data class VisitReportsModel(
    val address: String = "",
    val date: String = "",
    val name: String = "",
    val time: String = ""
) : Serializable