package com.educode.logincompose.data.service

import com.educode.logincompose.data.service.dto.ListResponse
import com.educode.logincompose.data.service.dto.LoginResponse
import com.educode.logincompose.data.service.dto.RegisterResponse
import com.educode.logincompose.data.source.RemoteDataSource
import com.google.gson.JsonObject
import retrofit2.Response
import javax.inject.Inject

class ReqresServiceDataSource @Inject constructor(private val service: ReqresService): RemoteDataSource{
    override suspend fun signUp(email: String, password: String): Response<RegisterResponse> {
        val json = JsonObject()
        json.addProperty("email", email)
        json.addProperty("password", password)
        return service.signUp(json)
    }

    override suspend fun logIn(email: String, password: String): Response<LoginResponse> {
        val json = JsonObject()
        json.addProperty("email", email)
        json.addProperty("password", password)
        return service.logIn(json)
    }

    override suspend fun getUsers(): Response<ListResponse> {
        return service.getUsers()
    }
}