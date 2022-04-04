package com.example.qadri.mvvm.model.reports

data class Movement(
    val last_day: LastDay = LastDay(),
    val today: TodayX = TodayX(),
    val variance: Variance = Variance()
)