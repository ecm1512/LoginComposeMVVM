package com.educode.logincompose.domain.usecases

import com.educode.logincompose.domain.model.User
import com.educode.logincompose.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
){

    suspend operator fun invoke(): List<User>{
        return userRepository.getUsers()
    }
}