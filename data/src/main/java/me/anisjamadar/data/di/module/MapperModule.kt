package me.anisjamadar.data.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.anisjamadar.data.local.entity.ArticleEntity
import me.anisjamadar.data.mapper.Mapper
import me.anisjamadar.data.mapper.TopHeadlineMapper
import me.anisjamadar.domain.model.Article
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Singleton
    @Provides
    fun providesTopHeadlineMapper(): Mapper<ArticleEntity, Article> {
        return TopHeadlineMapper()
    }

}