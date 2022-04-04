package com.example.qadri.mvvm.model.BranchVisitReport


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BranchVisitSubItem(
    @SerializedName("answer")
    var answer: String = "",
    @SerializedName("name")
    var name: String = "",
    @SerializedName("remarks")
    var remarks:String = "",
    @SerializedName("key")
    var key:String = ""
) : Serializable