package me.anisjamadar.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import me.anisjamadar.data.api.NetworkService
import me.anisjamadar.domain.model.Article
import me.anisjamadar.domain.repository.SearchNewsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchNewsRepositoryImpl @Inject constructor(
    private val networkService: NetworkService
) : SearchNewsRepository {
    override fun getTopHeadlines(country: String, query: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadlinesBySearch(country, query))
        }.map {
            it.articles
        }
    }
}