package com.example.qadri.mvvm.model.reports

data class Deposit(
    val NTB: NTB,
    val daily_deposit: DailyDeposit,
    val movement: Movement,
    val on_off_target: OnOffTarget,
    val tail: String = "",
    val tier_wise: TierWise,
    val top_150: Top150
)