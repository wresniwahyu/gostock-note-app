package com.gostock.util.constant

sealed class Screens(val route: String) {
    object Login : Screens("auth/login")
    object Register : Screens("auth/register")
    object Home : Screens("home")
    object AddNote : Screens("note/add")

    object NoteDetail : Screens("detail/{id}/{title}/{note}/{date}") {
        fun createRoute(id: String, title: String, note: String, date: String) = "detail/$id/$title/$note/$date"
    }
}