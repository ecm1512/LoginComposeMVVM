package com.educode.logincompose.data.repository

import com.educode.logincompose.data.source.RemoteDataSource
import com.educode.logincompose.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): AuthRepository {
    override suspend fun signUp(email: String, password: String): Boolean {
        return try{
            (remoteDataSource.signUp(email, password).body()?.id ?: 0) > 0
        } catch (e: Exception){
            false
        }
    }

    override suspend fun logIn(email: String, password: String): Boolean {
        return try{
            (remoteDataSource.logIn(email, password).body()?.token ?: "").isNullOrEmpty().not()
        } catch (e: Exception){
            false
        }
    }
}