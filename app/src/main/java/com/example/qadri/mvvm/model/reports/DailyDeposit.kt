package com.example.qadri.mvvm.model.reports

data class DailyDeposit(
    val current_year: CurrentYear = CurrentYear(),
    val last_year: LastYear = LastYear(),
    val today: Today = Today()
)