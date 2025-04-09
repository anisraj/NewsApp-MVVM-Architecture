package me.anisjamadar.domain.usecase

import androidx.paging.PagingData
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import me.anisjamadar.domain.model.Article
import me.anisjamadar.domain.repository.NewsRepositoryByPagination
import javax.inject.Inject

@ViewModelScoped
class TopHeadlineByPaginationUseCase @Inject constructor(
    private val newsRepository: NewsRepositoryByPagination
) {
    fun getTopHeadlinesByPagination(country: String): Flow<PagingData<Article>> {
        return newsRepository.getTopHeadlinesByPagination(country)
    }
}