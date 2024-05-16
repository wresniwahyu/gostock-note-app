package com.gostock.note

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gostock.local.UserPref

@Composable
fun MainNavigation(userPref: UserPref) {
    val navController = rememberNavController()
    val startDestination = if (userPref.accessToken.isNullOrBlank()) {
        "onboarding"
    } else {
        "home"
    }

    NavHost(navController = navController, startDestination = startDestination) {
//        composable("onboarding") { OnboardingScreen(navController = navController) }
//        composable("home") { HomeScreen(navController = navController) }

    }
}