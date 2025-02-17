package me.anisjamadar.newsapp_mvvm_architecture.di.module

import dagger.Binds
import dagger.Module
import me.anisjamadar.newsapp_mvvm_architecture.data.repository.NewsRepository
import me.anisjamadar.newsapp_mvvm_architecture.data.repository.NewsRepositoryImpl
import javax.inject.Singleton

@Module
interface ApplicationBindings {
    @Binds
    @Singleton
    fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository
}