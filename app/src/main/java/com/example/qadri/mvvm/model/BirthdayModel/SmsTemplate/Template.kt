package com.example.qadri.mvvm.model.BirthdayModel.SmsTemplate


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Template(
    @SerializedName("record_id")
    val recordId: String = "",
    @SerializedName("company_id")
    val companyId: String = "",
    @SerializedName("templated_type")
    val templatedType: String = "",
    @SerializedName("message")
    val message: String = "",
    @SerializedName("created_at")
    val createdAt: String = "",
    @SerializedName("created_by")
    val createdBy: String = "",
    @SerializedName("status")
    val status: String = "",
    @SerializedName("template_status")
    val templateStatus: String = ""
): Parcelable