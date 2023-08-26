package com.educode.logincompose.data.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ReqresClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://reqres.in/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: ReqresService = retrofit.create(ReqresService::class.java)
}