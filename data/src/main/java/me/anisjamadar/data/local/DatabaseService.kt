package me.anisjamadar.data.local

import kotlinx.coroutines.flow.Flow
import me.anisjamadar.data.local.entity.ArticleEntity

interface DatabaseService {
    fun flowOfArticles(): Flow<List<ArticleEntity>>
    suspend fun deleteAllAndUpdate(articles: List<ArticleEntity>)
}