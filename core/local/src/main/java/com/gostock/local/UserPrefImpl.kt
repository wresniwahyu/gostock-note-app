package com.gostock.local

import android.content.SharedPreferences
import javax.inject.Inject

class UserPrefImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
): UserPref {

    companion object {
        private const val KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN"
    }

    private var _accessToken = ""

    override var accessToken: String
        get() = getAccessTokenCompat()
        set(value) {
            _accessToken = value
            setString(KEY_ACCESS_TOKEN, value)
        }

    private fun getAccessTokenCompat(): String {
        if (_accessToken.isNotBlank()){
            return _accessToken
        }

        val accessToken = getString(KEY_ACCESS_TOKEN, "")
        _accessToken = accessToken
        return accessToken
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