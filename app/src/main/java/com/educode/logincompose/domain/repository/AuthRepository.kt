package com.educode.logincompose.domain.repository

interface AuthRepository {
    suspend fun signUp(email: String, password:String): Boolean
    suspend fun logIn(email: String, password:String): Boolean
}