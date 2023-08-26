package com.educode.logincompose.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Destinations(
    val route: String,
    val arguments: List<NamedNavArgument>
){
    object Login: Destinations("login", emptyList())
    object SignUp: Destinations("signUp", emptyList())
    object Home: Destinations("home", emptyList())
}
