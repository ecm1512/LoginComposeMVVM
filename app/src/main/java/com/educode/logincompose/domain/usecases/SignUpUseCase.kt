package com.educode.logincompose.domain.usecases

import com.educode.logincompose.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase@Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Boolean {
        return authRepository.signUp(email,password)
    }
}