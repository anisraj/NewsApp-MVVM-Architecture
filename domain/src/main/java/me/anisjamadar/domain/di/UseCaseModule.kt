package me.anisjamadar.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import me.anisjamadar.domain.repository.NewsRepository
import me.anisjamadar.domain.usecase.TopHeadlineUseCase

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideTopHeadlineUseCase(newsRepository: NewsRepository): TopHeadlineUseCase {
        return TopHeadlineUseCase(newsRepository)
    }
}