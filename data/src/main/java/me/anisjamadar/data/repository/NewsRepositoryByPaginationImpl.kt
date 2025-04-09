package me.anisjamadar.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import me.anisjamadar.domain.model.Article
import me.anisjamadar.domain.repository.NewsRepositoryByPagination
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryByPaginationImpl @Inject constructor(
    private val pagingSource: NewsPagingSource
) : NewsRepositoryByPagination {
    override fun getTopHeadlinesByPagination(country: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { pagingSource }
        ).flow
    }
}