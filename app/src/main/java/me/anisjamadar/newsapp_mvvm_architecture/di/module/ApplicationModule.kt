package me.anisjamadar.newsapp_mvvm_architecture.di.module

import android.content.Context
import android.util.Log
import dagger.Lazy
import dagger.Module
import dagger.Provides
import me.anisjamadar.newsapp_mvvm_architecture.BuildConfig
import me.anisjamadar.newsapp_mvvm_architecture.NewsApplication
import me.anisjamadar.newsapp_mvvm_architecture.data.UserAgentProvider
import me.anisjamadar.newsapp_mvvm_architecture.data.api.NetworkService
import me.anisjamadar.newsapp_mvvm_architecture.data.api.NewsApiInterceptor
import me.anisjamadar.newsapp_mvvm_architecture.di.ApiInterceptor
import me.anisjamadar.newsapp_mvvm_architecture.di.ApplicationContext
import me.anisjamadar.newsapp_mvvm_architecture.di.ApplicationScope
import me.anisjamadar.newsapp_mvvm_architecture.di.BaseClient
import me.anisjamadar.newsapp_mvvm_architecture.di.BaseUrl
import me.anisjamadar.newsapp_mvvm_architecture.di.LoggingInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = [ApplicationBindings::class])
class ApplicationModule {

    @ApplicationContext
    @Provides
    fun provideContext(application: NewsApplication): Context {
        return application
    }

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = "https://newsapi.org/v2/"

    @Provides
    @ApplicationScope
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @ApplicationScope
    @LoggingInterceptor
    fun provideLoggingInterceptor(): Interceptor {
        val logger = HttpLoggingInterceptor { m -> Log.d("okhttp", m) }
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }

    @Provides
    @ApplicationScope
    @ApiInterceptor
    fun provideNewsApiInterceptor(
        userAgentProvider: UserAgentProvider
    ): Interceptor {
        return NewsApiInterceptor(
            userAgentProvider.getUserAgent()
        )
    }

    @Provides
    @ApplicationScope
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
    @ApplicationScope
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