package me.anisjamadar.data.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideVersionName(@ApplicationContext context: Context): String {
        return context.packageManager.getPackageInfo(context.packageName, 0).versionName ?: "0.0"
    }

    @Singleton
    @Provides
    @Named("applicationId")
    fun provideApplicationId(@ApplicationContext context: Context): String {
        return context.packageName
    }

    @Singleton
    @Provides
    fun provideUserAgentProvider(
        @Named("versionName") versionName: String,
        @Named("applicationId") applicationId: String
    ): UserAgentProvider {
        return UserAgentProvider(versionName, applicationId)
    }
}