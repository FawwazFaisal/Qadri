package com.example.qadri.mvvm.model.notification

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NotificationList(
    val android_push_id: String,
    val apple_push_id: String,
    val creation_time: String,
    val email_address: String,
    val is_read: String,
    val message: String,
    val record_id: String,
    val sent_status: String,
    val status: String,
    val title: String,
    val type: String,
    val user_id: String
) : Parcelable