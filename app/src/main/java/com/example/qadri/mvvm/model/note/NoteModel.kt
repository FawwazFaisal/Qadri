package com.example.qadri.mvvm.model.note


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class NoteModel(
    @SerializedName("title")
    @Expose
    val title: String? = "",
    @SerializedName("note")
    @Expose
    val note: String? = "",
    @SerializedName("date")
    @Expose
    val date: String? = ""
)