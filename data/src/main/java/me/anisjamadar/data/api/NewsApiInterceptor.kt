package me.anisjamadar.data.api

import me.anisjamadar.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class NewsApiInterceptor(
    private val userAgent: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
            .header("X-Api-Key", BuildConfig.API_KEY)
            .header("User-Agent", userAgent)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}