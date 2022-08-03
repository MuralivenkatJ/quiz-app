package com.example.quiz

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Application {

    @GET("api.php?")
    fun getQuizData(
        @Query("category") c: Int,
        @Query("difficulty") d: String,
        @Query("amount") a: Int = 10,
        @Query("type") t: String = "multiple"
    ): Call<QuizAPIResponse>

}