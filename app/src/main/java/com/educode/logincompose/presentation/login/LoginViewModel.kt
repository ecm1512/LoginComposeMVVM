package com.educode.logincompose.presentation.login

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.educode.logincompose.R
import com.educode.logincompose.domain.usecases.LogInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val logInUseCase: LogInUseCase
): ViewModel() {

    val state: MutableState<LoginState> = mutableStateOf(LoginState())

    fun login(email: String, password: String){

        viewModelScope.launch {

            state.value = state.value.copy(displayProgressBar = true)

            val result = async(Dispatchers.IO){ logInUseCase(email,password) }

            val errorMessage = if(email.isBlank() || password.isBlank()) {
                R.string.error_input_empty
            } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                R.string.error_not_a_valid_email
            } else if(result.await().not()){
                R.string.error_invalid_credentials
            } else null

            errorMessage?.let {
                state.value = state.value.copy(errorMessage = it)
                state.value = state.value.copy(displayProgressBar = false)
                return@launch
            }

            state.value = state.value.copy(email = email, password = password)
            state.value = state.value.copy(displayProgressBar = false)
            state.value = state.value.copy(successLogin = true)

        }


    }

    fun hideErrorDialog() {
        state.value = state.value.copy(
            errorMessage = null
        )
    }
}