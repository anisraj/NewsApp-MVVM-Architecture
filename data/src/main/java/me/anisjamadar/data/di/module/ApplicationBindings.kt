package me.anisjamadar.data.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.anisjamadar.data.repository.NewsRepositoryImpl
import me.anisjamadar.domain.repository.NewsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ApplicationBindings {
    @Binds
    @Singleton
    fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository
}