package com.example.qadri.mvvm.model.BirthdayModel.SmsTemplate


import com.google.gson.annotations.SerializedName

data class TemplateModel(
    @SerializedName("template")
    val template: List<Template> = listOf()
)