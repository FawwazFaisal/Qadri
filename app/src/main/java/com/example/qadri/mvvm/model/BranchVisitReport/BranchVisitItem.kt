package com.example.qadri.mvvm.model.BranchVisitReport


import com.google.gson.annotations.SerializedName

data class BranchVisitItem(
    @SerializedName("category_name")
    val categoryName: String = "",
    @SerializedName("category_id")
    val categoryId: String = "",
    @SerializedName("sub_categories")
    val branchVisitSubItems: ArrayList<BranchVisitSubItem> = arrayListOf()
)