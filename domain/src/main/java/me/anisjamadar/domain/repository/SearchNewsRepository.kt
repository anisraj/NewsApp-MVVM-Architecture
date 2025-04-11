package me.anisjamadar.domain.repository

import kotlinx.coroutines.flow.Flow
import me.anisjamadar.domain.model.Article

interface SearchNewsRepository {
    fun getTopHeadlines(country: String, query: String): Flow<List<Article>>
}