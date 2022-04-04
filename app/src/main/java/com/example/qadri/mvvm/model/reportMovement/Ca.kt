package com.example.qadri.mvvm.model.reportMovement

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Ca(
    val customer_name: String = "",
    val last_day: String = "",
    val today: String = "",
    val variance: String = ""
): Parcelable