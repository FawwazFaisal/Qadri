package com.example.qadri.mvvm.model.reportMovement

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Deposit(
    val ca: ArrayList<Ca>,
    val sa: ArrayList<Sa>,
    val td: ArrayList<Td>,
    val total: ArrayList<Total>
): Parcelable