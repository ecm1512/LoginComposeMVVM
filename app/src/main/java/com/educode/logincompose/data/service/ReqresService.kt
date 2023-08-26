package com.educode.logincompose.data.service

import com.educode.logincompose.data.service.dto.ListResponse
import com.educode.logincompose.data.service.dto.LoginResponse
import com.educode.logincompose.data.service.dto.RegisterResponse
import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ReqresService {

    @POST("register")
    suspend fun signUp(
        @Body body: JsonObject
    ): Response<RegisterResponse>

    @POST("login")
    suspend fun logIn(
        @Body body: JsonObject
    ): Response<LoginResponse>

    @GET("users")
    suspend fun getUsers(): Response<ListResponse>
}