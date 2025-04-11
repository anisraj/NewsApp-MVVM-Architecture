package me.anisjamadar.data.api

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import me.anisjamadar.domain.model.TopHeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET("top-headlines")
    suspend fun getTopHeadlinesByPagination(@Query("country") country: String,
                                            @Query("page") page: Int,
                                            @Query("pageSize") pageSize: Int = PAGE_SIZE): TopHeadlinesResponse

    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): TopHeadlinesResponse

    @GET("top-headlines")
    suspend fun getTopHeadlinesBySearch(@Query("country") country: String,
                                        @Query("q") query: String): TopHeadlinesResponse

}