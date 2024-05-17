package com.gostock.local

import android.content.SharedPreferences
import javax.inject.Inject

class UserPrefImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
): UserPref {

    companion object {
        private const val KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN"
        private const val KEY_USER_NAME = "KEY_USER_NAME"
    }

    private var _accessToken = ""
    private var _username = ""

    override var accessToken: String
        get() = getAccessTokenCompat()
        set(value) {
            _accessToken = value
            setString(KEY_ACCESS_TOKEN, value)
        }

    override var username: String
        get() = getUserNameCompat()
        set(value) {
            _username = value
            setString(KEY_USER_NAME, value)
        }

    private fun getAccessTokenCompat(): String {
        if (_accessToken.isNotBlank()){
            return _accessToken
        }

        val accessToken = getString(KEY_ACCESS_TOKEN, "")
        _accessToken = accessToken
        return accessToken
    }

    private fun getUserNameCompat(): String {
        if (_username.isNotBlank()){
            return _username
        }

        val username = getString(KEY_USER_NAME, "")
        _username = username
        return username
    }

    private fun setString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    private fun getString(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) as String
    }

    override fun clear() {
        sharedPreferences.edit().clear().apply()
    }

}