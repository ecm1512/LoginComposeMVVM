package com.educode.logincompose.data.source

import com.educode.logincompose.data.service.dto.ListResponse
import com.educode.logincompose.data.service.dto.LoginResponse
import com.educode.logincompose.data.service.dto.RegisterResponse
import retrofit2.Response

interface RemoteDataSource {
    suspend fun signUp(email: String, password: String): Response<RegisterResponse>

    suspend fun logIn(email: String, password: String): Response<LoginResponse>

    suspend fun getUsers(): Response<ListResponse>
}