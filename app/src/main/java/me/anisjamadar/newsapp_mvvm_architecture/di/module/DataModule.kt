package me.anisjamadar.newsapp_mvvm_architecture.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.anisjamadar.newsapp_mvvm_architecture.BuildConfig
import me.anisjamadar.newsapp_mvvm_architecture.data.UserAgentProvider
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    @Named("versionName")
    fun provideVersionName() = BuildConfig.VERSION_NAME

    @Singleton
    @Provides
    @Named("applicationId")
    fun provideApplicationId() = BuildConfig.APPLICATION_ID

    @Singleton
    @Provides
    fun provideUserAgentProvider(
        @Named("versionName") versionName: String,
        @Named("applicationId") applicationId: String
    ): UserAgentProvider {
        return UserAgentProvider(versionName, applicationId)
    }
}