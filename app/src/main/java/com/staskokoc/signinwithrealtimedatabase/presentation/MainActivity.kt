package com.staskokoc.signinwithrealtimedatabase.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.staskokoc.signinwithrealtimedatabase.presentation.navigation.InitNavHost
import com.staskokoc.signinwithrealtimedatabase.presentation.signin.SignupScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            InitNavHost(navController = navController)
        }
    }
}