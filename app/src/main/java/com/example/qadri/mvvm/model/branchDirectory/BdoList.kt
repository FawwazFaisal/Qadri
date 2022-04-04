package com.example.qadri.mvvm.model.branchDirectory

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BdoList(
    val activation_code: String,
    val android_push_id: String,
    val apple_push_id: String,
    val branch_id: String,
    val comments: String,
    val company_id: String,
    val creation_date: String,
    val date_of_birth: String,
    val date_of_joining: String,
    val date_of_resign: String,
    val date_of_transfer: String,
    val designation_id: String,
    val device_id: String,
    val email_address: String,
    val email_verification_at: String,
    val email_verification_status: String,
    val emp_code: String,
    val first_name: String,
    val gender: String,
    val is_live: String,
    val is_new_user: String,
    val is_resigned: String,
    val last_change_password_date: String,
    val last_login_time: String,
    val last_name: String,
    val login_id: String,
    val num_of_login_attempts: String,
    val parallel_to: String,
    val password: String,
    val phone_number: String,
    val profile_img: String,
    val status: String,
    val temp_key: String,
    val token: String,
    val user_id: String,
    val user_type: String

): Parcelable