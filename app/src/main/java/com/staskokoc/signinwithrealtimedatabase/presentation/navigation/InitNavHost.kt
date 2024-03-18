package com.staskokoc.signinwithrealtimedatabase.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.staskokoc.signinwithrealtimedatabase.presentation.mainscreen.MainScreen
import com.staskokoc.signinwithrealtimedatabase.presentation.signin.LoginScreen
import com.staskokoc.signinwithrealtimedatabase.presentation.signin.SignupScreen

@Composable
fun InitNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "SignupScreen") {
        composable("SignupScreen") {
            SignupScreen(navController = navController)
        }
        composable("LoginScreen") {
            LoginScreen(navController = navController)
        }
        composable("MainScreen") {
            MainScreen()
        }
    }
}