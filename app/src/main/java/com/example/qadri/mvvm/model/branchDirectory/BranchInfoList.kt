package com.example.qadri.mvvm.model.branchDirectory

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BranchInfoList(
    val area_id: String,
    val bdo_list: List<BdoList>,
    val bm_name: String,
    val branch_code: String,
    val branch_name: String,
    val company_id: String,
    val created_by: String,
    val created_on: String,
    val latitude: String,
    val region_name: String,
    val longitude: String,
    val record_id: String,
    val region_id: String,
    val status: String,
    val updated_on: String
): Parcelable