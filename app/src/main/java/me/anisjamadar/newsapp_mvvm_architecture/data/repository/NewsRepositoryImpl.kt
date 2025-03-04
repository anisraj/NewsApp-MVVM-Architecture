package me.anisjamadar.newsapp_mvvm_architecture.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.anisjamadar.newsapp_mvvm_architecture.data.api.NetworkService
import me.anisjamadar.newsapp_mvvm_architecture.data.model.Article
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