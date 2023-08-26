package com.educode.logincompose.presentation.home

import androidx.annotation.StringRes
import com.educode.logincompose.domain.model.User

data class HomeState(
    val users: List<User> = mutableListOf(User()),
    val successHome: Boolean = false,
    val displayProgressBar: Boolean = false,
    @StringRes val errorMessage: Int? = null
)