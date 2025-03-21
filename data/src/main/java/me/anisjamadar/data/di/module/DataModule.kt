package me.anisjamadar.data.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.anisjamadar.data.BuildConfig
import me.anisjamadar.data.UserAgentProvider
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    @Named("versionName")
    fun provideVersionName() = "1.0"

    @Singleton
    @Provides
    @Named("applicationId")
    fun provideApplicationId() = "me.anisjamadar.newsapp_mvvm_architecture"

    @Singleton
    @Provides
    fun provideUserAgentProvider(
        @Named("versionName") versionName: String,
        @Named("applicationId") applicationId: String
    ): UserAgentProvider {
        return UserAgentProvider(versionName, applicationId)
    }
}