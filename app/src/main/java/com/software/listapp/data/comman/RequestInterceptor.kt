package com.software.listapp.data.comman

import com.software.listapp.utils.SharedPref
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor constructor(private val pref: SharedPref) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
        return chain.proceed(newRequest.build())
    }
}