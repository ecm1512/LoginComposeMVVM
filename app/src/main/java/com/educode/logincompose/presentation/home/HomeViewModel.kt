package com.educode.logincompose.presentation.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.educode.logincompose.R
import com.educode.logincompose.domain.usecases.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
): ViewModel(){

    val state: MutableState<HomeState> = mutableStateOf(HomeState())

    fun getUsers(){
        viewModelScope.launch {
            state.value = state.value.copy(displayProgressBar = true)

            val result = async(Dispatchers.IO){ getUsersUseCase() }

            val errorMessage = if(result.await().isNullOrEmpty()) {
                R.string.error_service
            }else null

            errorMessage?.let {
                state.value = state.value.copy(errorMessage = it)
                state.value = state.value.copy(displayProgressBar = false)
                return@launch
            }

            state.value = state.value.copy(users = result.await())
            state.value = state.value.copy(displayProgressBar = false)
            state.value = state.value.copy(successHome = true)
        }
    }

    fun hideErrorDialog() {
        state.value = state.value.copy(
            errorMessage = null
        )
    }

}