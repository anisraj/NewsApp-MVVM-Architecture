package me.anisjamadar.newsapp_mvvm_architecture.data.api

import me.anisjamadar.newsapp_mvvm_architecture.data.model.TopHeadlinesResponse
import me.anisjamadar.newsapp_mvvm_architecture.utils.AppConstant.PAGE_SIZE
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String,
                                @Query("page") page: Int,
                                @Query("pageSize") pageSize: Int = PAGE_SIZE): TopHeadlinesResponse
}