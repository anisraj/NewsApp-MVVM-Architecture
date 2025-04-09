package me.anisjamadar.domain.repository

import kotlinx.coroutines.flow.Flow
import me.anisjamadar.domain.model.Article

interface OfflineFirstNewsRepository {
    fun flowOfArticles(): Flow<List<Article>>
    suspend fun updateArticles(country: String): Result<List<Article>>
}