package me.anisjamadar.newsapp_mvvm_architecture.data.repository

import kotlinx.coroutines.flow.Flow
import me.anisjamadar.newsapp_mvvm_architecture.data.model.Article

interface NewsRepository {
    fun getTopHeadlines(country: String): Flow<List<Article>>
}