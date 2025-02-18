package me.anisjamadar.newsapp_mvvm_architecture.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import me.anisjamadar.newsapp_mvvm_architecture.data.api.NetworkService
import me.anisjamadar.newsapp_mvvm_architecture.data.model.Article
import me.anisjamadar.newsapp_mvvm_architecture.di.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class NewsRepositoryImpl @Inject constructor(
    private val networkService: NetworkService
) : NewsRepository {
    override fun getTopHeadlines(country: String): Flow<List<Article>> {
        return flow {
            emit(networkService.getTopHeadlines(country))
        }.map {
            it.articles
        }
    }
}