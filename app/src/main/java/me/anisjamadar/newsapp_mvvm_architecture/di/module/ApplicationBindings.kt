package me.anisjamadar.newsapp_mvvm_architecture.di.module

import dagger.Binds
import dagger.Module
import me.anisjamadar.newsapp_mvvm_architecture.data.repository.NewsRepository
import me.anisjamadar.newsapp_mvvm_architecture.data.repository.NewsRepositoryImpl
import me.anisjamadar.newsapp_mvvm_architecture.di.ApplicationScope
import javax.inject.Singleton

@Module
interface ApplicationBindings {
    @Binds
    @ApplicationScope
    fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository
}