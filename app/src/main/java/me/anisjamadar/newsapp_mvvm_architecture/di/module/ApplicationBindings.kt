package me.anisjamadar.newsapp_mvvm_architecture.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.anisjamadar.newsapp_mvvm_architecture.data.repository.NewsRepository
import me.anisjamadar.newsapp_mvvm_architecture.data.repository.NewsRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ApplicationBindings {
    @Binds
    @Singleton
    fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository
}