package com.educode.logincompose.data.repository

import com.educode.logincompose.data.service.dto.UserDTO
import com.educode.logincompose.data.source.RemoteDataSource
import com.educode.logincompose.domain.model.User
import com.educode.logincompose.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val mapper: UserDataMapper
): UserRepository{

    override suspend fun getUsers(): List<User> {
        val data = remoteDataSource.getUsers()
        return if(data.isSuccessful){
            data.body()?.data.orEmpty().map(mapper::map)
        }else{
            emptyList()
        }
    }
}

class UserDataMapper @Inject constructor(){
    fun map(user: UserDTO): User {
        return with(user){
            User(
                id,
                email,
                firstName,
                lastName,
                avatar
            )
        }
    }

}