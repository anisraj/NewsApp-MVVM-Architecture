package me.anisjamadar.data.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.anisjamadar.data.repository.NewsRepositoryByPaginationImpl
import me.anisjamadar.data.repository.NewsRepositoryImpl
import me.anisjamadar.data.repository.OfflineFirstNewsRepositoryImpl
import me.anisjamadar.domain.repository.NewsRepository
import me.anisjamadar.domain.repository.NewsRepositoryByPagination
import me.anisjamadar.domain.repository.OfflineFirstNewsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ApplicationBindings {
    @Binds
    @Singleton
    fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository

    @Binds
    @Singleton
    fun bindNewsRepositoryByPagination(impl: NewsRepositoryByPaginationImpl): NewsRepositoryByPagination

    @Binds
    @Singleton
    fun bindOfflineFirstNewsRepository(impl: OfflineFirstNewsRepositoryImpl): OfflineFirstNewsRepository
}