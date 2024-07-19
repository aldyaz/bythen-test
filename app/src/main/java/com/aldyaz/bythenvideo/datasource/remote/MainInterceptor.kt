package com.aldyaz.bythenvideo.datasource.remote

import okhttp3.Interceptor
import okhttp3.Response

class MainInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        return chain.proceed(request.build())
    }
}