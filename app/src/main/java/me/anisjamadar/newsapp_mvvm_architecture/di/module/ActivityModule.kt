package me.anisjamadar.newsapp_mvvm_architecture.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import me.anisjamadar.newsapp_mvvm_architecture.data.repository.NewsRepository
import me.anisjamadar.newsapp_mvvm_architecture.di.ActivityContext
import me.anisjamadar.newsapp_mvvm_architecture.di.ActivityScope
import me.anisjamadar.newsapp_mvvm_architecture.ui.top_headlines.TopHeadlineAdapter
import me.anisjamadar.newsapp_mvvm_architecture.ui.top_headlines.TopHeadlineViewModel

@Module
class ActivityModule {
    @ActivityContext
    @ActivityScope
    @Provides
    fun providesContext(activity: AppCompatActivity): Context {
        return activity
    }

    @ActivityScope
    @Provides
    fun providesTopHeadlineAdapter() = TopHeadlineAdapter(ArrayList())

}