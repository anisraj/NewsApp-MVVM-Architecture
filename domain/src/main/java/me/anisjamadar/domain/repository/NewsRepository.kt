package me.anisjamadar.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.anisjamadar.domain.model.Article

interface NewsRepository {
    fun getTopHeadlinesByPagination(country: String): Flow<PagingData<Article>>

    suspend fun getTopHeadlines(country: String): Result<List<Article>>
}