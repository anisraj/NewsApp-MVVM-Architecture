package me.anisjamadar.data.mapper

import me.anisjamadar.data.local.entity.ArticleEntity
import me.anisjamadar.data.local.entity.SourceEntity
import me.anisjamadar.domain.model.Article
import me.anisjamadar.domain.model.Source

class TopHeadlineMapper : Mapper<ArticleEntity, Article> {
    override fun transformToEntity(domainModel: Article): ArticleEntity {
        return ArticleEntity(
            id = 0,
            title = domainModel.title,
            description = domainModel.description,
            url = domainModel.url,
            imageUrl = domainModel.imageUrl,
            sourceEntity = SourceEntity(
                id = domainModel.source?.id,
                name = domainModel.source?.name
            )
        )
    }

    override fun transformToDomainModel(entity: ArticleEntity): Article {
        return Article(
            title = entity.title,
            description = entity.description,
            url = entity.url,
            imageUrl = entity.imageUrl,
            source = Source(
                id = entity.sourceEntity.id,
                name = entity.sourceEntity.name
            )
        )
    }
}