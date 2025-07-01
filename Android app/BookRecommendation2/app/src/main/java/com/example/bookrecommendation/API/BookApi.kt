package com.example.bookrecommendation.API

import com.example.bookrecommendation.Model.Book
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BookApi {
    @GET("popular")
    suspend fun getPopularBooks(): List<Book>

    @POST("recommend")
    suspend fun getRecommendations(@Body request: Map<String, String>): List<Book>
}