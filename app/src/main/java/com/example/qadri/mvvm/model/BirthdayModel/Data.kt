package com.example.qadri.mvvm.model.BirthdayModel


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("recent")
    val recent: ArrayList<BirthdayModel> = arrayListOf(),
    @SerializedName("today")
    val today: ArrayList<BirthdayModel> = arrayListOf(),
    @SerializedName("upcoming")
    val upcoming: ArrayList<BirthdayModel> = arrayListOf()
)