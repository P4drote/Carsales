package com.example.carsalesprueba.domain

import okhttp3.Interceptor
import okhttp3.Response

class MyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .get()
            .addHeader("x-rapidapi-key", "96afa298cbmsh913f910f914494cp110c39jsn01a32d68445e")
            .build()
        return chain.proceed(request)
    }
}
