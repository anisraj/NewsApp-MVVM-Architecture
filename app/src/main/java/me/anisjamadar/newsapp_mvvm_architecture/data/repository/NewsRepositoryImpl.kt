package me.anisjamadar.newsapp_mvvm_architecture.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import me.anisjamadar.newsapp_mvvm_architecture.data.api.NetworkService
import me.anisjamadar.newsapp_mvvm_architecture.data.model.Article
import me.anisjamadar.newsapp_mvvm_architecture.di.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class NewsRepositoryImpl @Inject constructor(
    private val pagingSource: NewsPagingSource
) : NewsRepository {
    override fun getTopHeadlines(country: String): Flow<PagingData<Article>> {
        return Pager(
            PagingConfig(pageSize = 10)
        ) {
            pagingSource
        }.flow
    }
}