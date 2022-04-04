package com.example.qadri.mvvm.model.tierWise

data class TierWise(
    val current: List<Current> = listOf(),
    val saving: List<Saving> = listOf(),
    val total: List<Total> = listOf()
)