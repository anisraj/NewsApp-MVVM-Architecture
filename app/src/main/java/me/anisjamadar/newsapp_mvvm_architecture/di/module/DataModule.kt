package me.anisjamadar.newsapp_mvvm_architecture.di.module

import dagger.Module
import dagger.Provides
import me.anisjamadar.newsapp_mvvm_architecture.BuildConfig
import me.anisjamadar.newsapp_mvvm_architecture.data.UserAgentProvider
import me.anisjamadar.newsapp_mvvm_architecture.di.ApplicationScope
import javax.inject.Named

@Module
object DataModule {

    @ApplicationScope
    @Provides
    @Named("versionName")
    fun provideVersionName() = BuildConfig.VERSION_NAME

    @ApplicationScope
    @Provides
    @Named("applicationId")
    fun provideApplicationId() = BuildConfig.APPLICATION_ID

    @ApplicationScope
    @Provides
    fun provideUserAgentProvider(
        @Named("versionName") versionName: String,
        @Named("applicationId") applicationId: String
    ): UserAgentProvider {
        return UserAgentProvider(versionName, applicationId)
    }
}