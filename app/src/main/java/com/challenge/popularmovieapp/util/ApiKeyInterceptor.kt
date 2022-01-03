package com.challenge.popularmovieapp.util

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newUrl = request.url.newBuilder().addQueryParameter(API_KEY_PARAMETER, API_KEY).build()
        val newRequest = request.newBuilder().url(newUrl).build()
        return chain.proceed(newRequest)
    }

    companion object {
        const val API_KEY_PARAMETER = "api_key"
        const val API_KEY =
            "03e29fa125608afd421e229dc71545cd" // this should be at least hidden with NDK help
    }
}