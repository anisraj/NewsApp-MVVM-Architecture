package me.anisjamadar.data.di.module

import android.content.Context
import android.util.Log
import androidx.room.Room
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.anisjamadar.data.BuildConfig
import me.anisjamadar.data.UserAgentProvider
import me.anisjamadar.data.api.NetworkService
import me.anisjamadar.data.api.NewsApiInterceptor
import me.anisjamadar.data.di.ApiInterceptor
import me.anisjamadar.data.di.BaseClient
import me.anisjamadar.data.di.BaseUrl
import me.anisjamadar.data.di.DatabaseName
import me.anisjamadar.data.di.LoggingInterceptor
import me.anisjamadar.data.local.AppDatabase
import me.anisjamadar.data.local.AppDatabaseService
import me.anisjamadar.data.local.DatabaseService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = "https://newsapi.org/v2/"

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    @LoggingInterceptor
    fun provideLoggingInterceptor(): Interceptor {
        val logger = HttpLoggingInterceptor { m -> Log.d("okhttp", m) }
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }

    @Provides
    @Singleton
    @ApiInterceptor
    fun provideNewsApiInterceptor(
        userAgentProvider: UserAgentProvider
    ): Interceptor {
        return NewsApiInterceptor(
            userAgentProvider.getUserAgent()
        )
    }

    @Provides
    @Singleton
    @BaseClient
    fun provideBaseClient(
        @LoggingInterceptor loggingInterceptor: Lazy<Interceptor>,
        @ApiInterceptor apiInterceptor: Interceptor
    ): OkHttpClient {
        val builder = OkHttpClient.Builder().apply {
            addInterceptor(apiInterceptor)
            if (BuildConfig.DEBUG) {
                addInterceptor(loggingInterceptor.get())
            }
        }
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory,
        @BaseClient okHttpClient: OkHttpClient
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        @DatabaseName databaseName: String
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            databaseName
        ).build()
    }

    @Provides
    @Singleton
    fun provideDatabaseService(
        appDatabase: AppDatabase
    ): DatabaseService {
        return AppDatabaseService(appDatabase)
    }

}