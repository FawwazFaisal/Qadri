package com.example.qadri.mvvm.model.trainingAndQuiz

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuizDetailItem(val question_id: String, val answer: String): Parcelable