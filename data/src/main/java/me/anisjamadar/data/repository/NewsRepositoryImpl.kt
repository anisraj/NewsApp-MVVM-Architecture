package me.anisjamadar.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.anisjamadar.data.api.NetworkService
import me.anisjamadar.domain.model.Article
import me.anisjamadar.domain.repository.NewsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val pagingSource: NewsPagingSource,
    private val networkService: NetworkService
) : NewsRepository {
    override fun getTopHeadlinesByPagination(country: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { pagingSource }
        ).flow
    }

    override suspend fun getTopHeadlines(country: String): Result<List<Article>> {
        return kotlin.runCatching {
            networkService.getTopHeadlines(country).articles
        }
    }
}