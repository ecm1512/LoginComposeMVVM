package com.educode.logincompose.domain.repository

import com.educode.logincompose.domain.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
}