package com.gostock.network.util

import com.gostock.local.UserPref
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AppInterceptor @Inject constructor(
    private val userPref: UserPref
) : Interceptor {

    companion object {
        private const val HEADER_AUTH = "Authorization"
        private const val BEARER = "Bearer"
    }

    private fun addHeaderInfo(chain: Interceptor.Chain): Request {
        val request = chain.request().newBuilder()

        if (userPref.accessToken.isNotBlank()) {
            request.addHeader(HEADER_AUTH, "$BEARER ${userPref.accessToken}")
        }

        return request.build()
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = addHeaderInfo(chain)
        return chain.proceed(request)
    }
}