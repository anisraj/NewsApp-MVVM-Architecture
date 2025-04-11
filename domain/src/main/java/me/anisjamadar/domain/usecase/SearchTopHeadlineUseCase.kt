package me.anisjamadar.domain.usecase

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import me.anisjamadar.domain.model.Article
import me.anisjamadar.domain.repository.SearchNewsRepository
import javax.inject.Inject

@ViewModelScoped
class SearchTopHeadlineUseCase @Inject constructor(
    private val newsRepository: SearchNewsRepository
) {
    fun getTopHeadlines(country: String, query: String): Flow<List<Article>> {
        return newsRepository.getTopHeadlines(country, query)
    }
}