package me.anisjamadar.data.repository

import me.anisjamadar.data.api.NetworkService
import me.anisjamadar.domain.model.Article
import me.anisjamadar.domain.repository.NewsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val networkService: NetworkService
) : NewsRepository {
    override suspend fun getTopHeadlines(country: String): Result<List<Article>> {
        return kotlin.runCatching {
            networkService.getTopHeadlines(country).articles
        }
    }
}