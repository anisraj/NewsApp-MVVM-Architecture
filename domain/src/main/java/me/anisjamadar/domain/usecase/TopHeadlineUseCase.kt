package me.anisjamadar.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.anisjamadar.domain.model.Article
import me.anisjamadar.domain.repository.NewsRepository

class TopHeadlineUseCase(
    private val newsRepository: NewsRepository
) {
    suspend fun getTopHeadlines(country: String): Result<List<Article>> {
        return newsRepository.getTopHeadlines(country)
    }

    fun getTopHeadlinesByPagination(country: String): Flow<PagingData<Article>> {
        return newsRepository.getTopHeadlinesByPagination(country)
    }
}