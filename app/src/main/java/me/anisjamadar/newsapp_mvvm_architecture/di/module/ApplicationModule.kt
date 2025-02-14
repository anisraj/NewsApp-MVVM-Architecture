package me.anisjamadar.newsapp_mvvm_architecture.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import me.anisjamadar.newsapp_mvvm_architecture.NewsApplication
import me.anisjamadar.newsapp_mvvm_architecture.di.ApplicationContext
import me.anisjamadar.newsapp_mvvm_architecture.di.BaseUrl
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: NewsApplication) {

    @ApplicationContext
    @Provides
    fun providesContext(): Context {
        return application
    }
}