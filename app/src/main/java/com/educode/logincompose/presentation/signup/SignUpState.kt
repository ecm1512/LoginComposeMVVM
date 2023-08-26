package com.educode.logincompose.presentation.signup

import androidx.annotation.StringRes

data class SignUpState(
    val successSignUp: Boolean = false,
    val displayProgressBar: Boolean = false,
    @StringRes val errorMessage: Int? = null
)
