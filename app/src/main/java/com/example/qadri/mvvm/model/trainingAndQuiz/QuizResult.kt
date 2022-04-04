package com.example.qadri.mvvm.model.trainingAndQuiz

data class QuizResult(
    val correct_answers: Int?,
    val incorrect_answers: Int?,
    val percentage: Double?,
    val result: String?,
    val total_questions: Int?
)