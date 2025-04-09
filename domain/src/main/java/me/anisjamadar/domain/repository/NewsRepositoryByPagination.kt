package me.anisjamadar.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.anisjamadar.domain.model.Article

interface NewsRepositoryByPagination {
    fun getTopHeadlinesByPagination(country: String): Flow<PagingData<Article>>
}