package me.anisjamadar.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.anisjamadar.data.api.NetworkService
import me.anisjamadar.data.local.DatabaseService
import me.anisjamadar.data.local.entity.ArticleEntity
import me.anisjamadar.data.mapper.Mapper
import me.anisjamadar.domain.model.Article
import me.anisjamadar.domain.repository.OfflineFirstNewsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfflineFirstNewsRepositoryImpl @Inject constructor(
    private val databaseService: DatabaseService,
    private val networkService: NetworkService,
    private val mapper: Mapper<ArticleEntity, Article>
) : OfflineFirstNewsRepository {

    override fun flowOfArticles(): Flow<List<Article>> {
        return databaseService.flowOfArticles().map { listOfArticleEntities ->
            listOfArticleEntities.map { articleEntity ->
                mapper.transformToDomainModel(articleEntity)
            }
        }
    }

    override suspend fun updateArticles(country: String): Result<List<Article>> {
        return kotlin.runCatching {
            networkService.getTopHeadlines(country).articles
        }.onSuccess { articles ->
            databaseService.deleteAllAndUpdate(articles.map { article ->
                mapper.transformToEntity(article)
            })
        }
    }
}