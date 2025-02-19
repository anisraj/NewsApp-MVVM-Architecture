package me.anisjamadar.newsapp_mvvm_architecture.data.api

import me.anisjamadar.newsapp_mvvm_architecture.BuildConfig
import me.anisjamadar.newsapp_mvvm_architecture.data.model.TopHeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET("top-headlines")
    @Headers("X-Api-Key: ${BuildConfig.API_KEY}", "User-Agent:me.anisjamadar.newsapp_mvvm_architecture/1.0")
    suspend fun getTopHeadlines(@Query("country") country: String): TopHeadlinesResponse
}