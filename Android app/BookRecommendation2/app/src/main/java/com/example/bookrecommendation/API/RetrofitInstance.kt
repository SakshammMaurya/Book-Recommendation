package com.example.bookrecommendation.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.2:5000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: BookApi = retrofit.create(BookApi::class.java)
}