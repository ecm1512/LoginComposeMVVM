package com.educode.logincompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.educode.logincompose.presentation.home.HomeScreen
import com.educode.logincompose.presentation.home.HomeViewModel
import com.educode.logincompose.presentation.login.LoginScreen
import com.educode.logincompose.presentation.login.LoginViewModel
import com.educode.logincompose.presentation.navigation.Destinations
import com.educode.logincompose.presentation.signup.SignUpScreen
import com.educode.logincompose.presentation.signup.SignUpViewModel
import com.educode.logincompose.presentation.theme.LoginComposeTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginComposeTheme {
                val navController = rememberAnimatedNavController()

                BoxWithConstraints {
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = Destinations.Login.route
                    ){
                        addLogin(navController)
                        addSignUp(navController)
                        addHome()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addLogin(
    navController: NavHostController
){
    composable(
        route = Destinations.Login.route,
        enterTransition = {
            slideInHorizontally (
                initialOffsetX = {1000},
                animationSpec = tween(500)
            )
        },
        exitTransition = {
            slideOutHorizontally (
                targetOffsetX = {-1000},
                animationSpec = tween(500)
            )
        },
        popEnterTransition = {
            slideInHorizontally (
                initialOffsetX = {-1000},
                animationSpec = tween(500)
            )
        },
        popExitTransition = {
            slideOutHorizontally (
                targetOffsetX = {1000},
                animationSpec = tween(500)
            )

        }
    ){
        val viewModel: LoginViewModel = hiltViewModel()
        val email = viewModel.state.value.email
        val password = viewModel.state.value.password

        if(viewModel.state.value.successLogin){
            LaunchedEffect(key1 = Unit){
                navController.navigate(
                    Destinations.Home.route
                ){
                    popUpTo(Destinations.Login.route){
                        inclusive = true
                    }
                }
            }
        }else{
            LoginScreen(
                state = viewModel.state.value,
                onLogin = viewModel::login,
                onNavigateToRegister = {
                    navController.navigate(Destinations.SignUp.route)
                },
                onDismissDialog = viewModel::hideErrorDialog
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addSignUp(
    navController: NavHostController
){
    composable(
        route = Destinations.SignUp.route,
        enterTransition = {
            slideInHorizontally (
                initialOffsetX = {1000},
                animationSpec = tween(500)
            )
        },
        exitTransition = {
            slideOutHorizontally (
                targetOffsetX = {-1000},
                animationSpec = tween(500)
            )
        },
        popEnterTransition = {
            slideInHorizontally (
                initialOffsetX = {-1000},
                animationSpec = tween(500)
            )
        },
        popExitTransition = {
            slideOutHorizontally (
                targetOffsetX = {1000},
                animationSpec = tween(500)
            )

        }
    ){
        val viewModel: SignUpViewModel = hiltViewModel()

        if(viewModel.state.value.successSignUp){
            navController.popBackStack()
        }else{
            SignUpScreen(
                state = viewModel.state.value,
                onRegister = viewModel::register,
                onBack = {
                    navController.popBackStack()
                },
                onDismissDialog = viewModel::hideErrorDialog
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addHome(){
    composable(
        route = Destinations.Home.route
    ){
        val viewModel: HomeViewModel = hiltViewModel()
        viewModel.getUsers()

        if(viewModel.state.value.successHome){
            HomeScreen(viewModel.state.value.users)
        }else{
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }


    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginComposeTheme {
        //SignUpScreen()
    }
}