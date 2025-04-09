package me.anisjamadar.domain.repository

import me.anisjamadar.domain.model.Article

interface NewsRepository {
    suspend fun getTopHeadlines(country: String): Result<List<Article>>
}