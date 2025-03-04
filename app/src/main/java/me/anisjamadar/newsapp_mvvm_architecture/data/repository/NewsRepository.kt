package me.anisjamadar.newsapp_mvvm_architecture.data.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.anisjamadar.newsapp_mvvm_architecture.data.model.Article

interface NewsRepository {
    fun getTopHeadlinesByPagination(country: String): Flow<PagingData<Article>>

    suspend fun getTopHeadlines(country: String): Result<List<Article>>
}