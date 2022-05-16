package com.example.qadri.mvvm.model.lov

data class LovResponse(
    val bank: List<String>,
    val categories: List<Category>,
    val company_products: List<String>,
    val company_visit_status: List<String>,
    val department: List<Department>,
    val payment_type: List<String>
)