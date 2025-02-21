package me.anisjamadar.newsapp_mvvm_architecture.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay
import me.anisjamadar.newsapp_mvvm_architecture.data.api.NetworkService
import me.anisjamadar.newsapp_mvvm_architecture.data.model.Article
import me.anisjamadar.newsapp_mvvm_architecture.di.ApplicationScope
import me.anisjamadar.newsapp_mvvm_architecture.utils.AppConstant.COUNTRY
import me.anisjamadar.newsapp_mvvm_architecture.utils.AppConstant.PAGE_SIZE
import javax.inject.Inject

@ApplicationScope
class NewsPagingSource @Inject constructor(
    private val networkService: NetworkService
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val position = params.key ?: 1
            val response = networkService.getTopHeadlines(
                COUNTRY,
                page = position
            )
            LoadResult.Page(
                data = response.articles,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position * PAGE_SIZE > (response.totalResults)
                ) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}