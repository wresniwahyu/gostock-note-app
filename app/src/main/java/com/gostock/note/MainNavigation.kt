package com.gostock.note

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gostock.auth.login.LoginScreen
import com.gostock.auth.register.RegisterScreen
import com.gostock.featurenote.addnote.AddNoteScreen
import com.gostock.featurenote.home.HomeScreen
import com.gostock.featurenote.notedetail.NoteDetailScreen
import com.gostock.local.UserPref
import com.gostock.util.constant.Screens

@Composable
fun MainNavigation(userPref: UserPref) {
    val navController = rememberNavController()
    val startDestination = if (userPref.accessToken.isNullOrBlank()) {
        Screens.Login.route
    } else {
        Screens.Home.route
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screens.Login.route) { LoginScreen(navController = navController) }
        composable(Screens.Register.route) { RegisterScreen(navController = navController) }
        composable(Screens.Home.route) {
            HomeScreen(
                navController = navController,
                userPref = userPref
            )
        }
        composable(Screens.AddNote.route) { AddNoteScreen(navController = navController) }
        composable(
            route = Screens.NoteDetail.route,
            arguments = listOf(
                navArgument("id") { type = NavType.StringType },
                navArgument("title") { type = NavType.StringType },
                navArgument("note") { type = NavType.StringType },
                navArgument("date") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id").orEmpty()
            val title = backStackEntry.arguments?.getString("title").orEmpty()
            val note = backStackEntry.arguments?.getString("note").orEmpty()
            val date = backStackEntry.arguments?.getString("date").orEmpty()
            NoteDetailScreen(
                navController = navController,
                id = id,
                title = title,
                note = note,
                date = date
            )
        }

    }
}