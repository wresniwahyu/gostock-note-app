package com.gostock.local

interface UserPref {
    var accessToken: String
    var username: String
    fun clear()
}