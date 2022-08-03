package com.example.quiz

data class QuizAPIResponse(
    val response_code: Int,
    val results: List<Quiz>
)
