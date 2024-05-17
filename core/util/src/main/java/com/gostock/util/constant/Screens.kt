package com.gostock.util.constant

sealed class Screens(val route: String) {
    object Login : Screens("auth/login")
    object Register : Screens("auth/register")
    object Home : Screens("home")
    object AddNote : Screens("note/add")
}