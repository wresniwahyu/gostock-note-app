package com.gostock.local

interface UserPref {
    var accessToken: String
    fun clear()
}