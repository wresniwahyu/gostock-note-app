package com.gostock.note

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gostock.auth.login.LoginScreen
import com.gostock.auth.register.RegisterScreen
import com.gostock.local.UserPref
import com.gostock.util.constant.Screens

@Composable
fun MainNavigation(userPref: UserPref) {
    val navController = rememberNavController()
    val startDestination = if (userPref.accessToken.isNullOrBlank()) {
        Screens.Login.route
    } else {
        "home"
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screens.Login.route) { LoginScreen(navController = navController) }
        composable(Screens.Register.route) { RegisterScreen() }//navController = navController) }
//        composable("home") { HomeScreen(navController = navController) }

    }
}