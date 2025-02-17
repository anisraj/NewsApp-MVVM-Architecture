package me.anisjamadar.newsapp_mvvm_architecture.data.api

import me.anisjamadar.newsapp_mvvm_architecture.data.model.TopHeadlinesResponse
import me.anisjamadar.newsapp_mvvm_architecture.utils.AppConstant.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET("top-headlines")
    @Headers("X-Api-Key: $API_KEY", "User-Agent:me.anisjamadar.newsapp_mvvm_architecture/1.0")
    suspend fun getTopHeadlines(@Query("country") country: String): TopHeadlinesResponse
}