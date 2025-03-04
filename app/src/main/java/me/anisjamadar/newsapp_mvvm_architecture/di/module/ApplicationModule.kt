package me.anisjamadar.newsapp_mvvm_architecture.di.module

import android.util.Log
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.anisjamadar.newsapp_mvvm_architecture.BuildConfig
import me.anisjamadar.newsapp_mvvm_architecture.data.UserAgentProvider
import me.anisjamadar.newsapp_mvvm_architecture.data.api.NetworkService
import me.anisjamadar.newsapp_mvvm_architecture.data.api.NewsApiInterceptor
import me.anisjamadar.newsapp_mvvm_architecture.di.ApiInterceptor
import me.anisjamadar.newsapp_mvvm_architecture.di.BaseClient
import me.anisjamadar.newsapp_mvvm_architecture.di.BaseUrl
import me.anisjamadar.newsapp_mvvm_architecture.di.LoggingInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [ApplicationBindings::class])
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
}