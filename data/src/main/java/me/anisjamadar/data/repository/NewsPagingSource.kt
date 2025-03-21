package me.anisjamadar.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import me.anisjamadar.data.api.NetworkService
import me.anisjamadar.domain.model.Article
import me.anisjamadar.util.AppConstant.COUNTRY
import me.anisjamadar.util.AppConstant.PAGE_SIZE
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsPagingSource @Inject constructor(
    private val networkService: NetworkService
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val position = params.key ?: 1
            val response = networkService.getTopHeadlinesByPagination(
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
            return LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}