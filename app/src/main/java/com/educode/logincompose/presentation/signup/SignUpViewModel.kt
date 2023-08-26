package com.educode.logincompose.presentation.signup

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.educode.logincompose.R
import com.educode.logincompose.domain.usecases.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
): ViewModel() {

    val state: MutableState<SignUpState> = mutableStateOf(SignUpState())

    fun register(email: String, password: String) {

        viewModelScope.launch {
            state.value = state.value.copy(displayProgressBar = true)

            val result = async(Dispatchers.IO) { signUpUseCase(email,password) }

            val errorMessage = if(email.isBlank() || password.isBlank() ){
                R.string.error_input_empty
            } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                R.string.error_not_a_valid_email
            } else if(result.await().not()){
                R.string.error_service
            }else null

            errorMessage?.let {
                state.value = state.value.copy(errorMessage = errorMessage)
                state.value = state.value.copy(displayProgressBar = false)
                return@launch
            }

            state.value = state.value.copy(displayProgressBar = false)
            state.value = state.value.copy(successSignUp = true)
        }

    }

    fun hideErrorDialog() {
        state.value = state.value.copy(
            errorMessage = null
        )
    }
}