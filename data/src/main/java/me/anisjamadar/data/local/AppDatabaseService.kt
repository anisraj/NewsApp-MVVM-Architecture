package me.anisjamadar.data.local

import kotlinx.coroutines.flow.Flow
import me.anisjamadar.data.local.entity.ArticleEntity

class AppDatabaseService (
    private val appDatabase: AppDatabase
) : DatabaseService {
    override fun flowOfArticles(): Flow<List<ArticleEntity>> {
        return appDatabase.articleDao().flowOfArticles()
    }

    override suspend fun deleteAllAndUpdate(articles: List<ArticleEntity>) {
        appDatabase.articleDao().deleteAllAndUpdate(articles)
    }
}