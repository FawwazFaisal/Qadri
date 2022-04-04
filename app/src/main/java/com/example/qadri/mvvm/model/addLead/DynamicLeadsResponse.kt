package com.example.qadri.mvvm.model.addLead

data class DynamicLeadsResponse(
    val section: String,
    val `data`: List<DynamicLeadsItem>
)