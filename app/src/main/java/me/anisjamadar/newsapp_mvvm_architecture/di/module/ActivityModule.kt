package me.anisjamadar.newsapp_mvvm_architecture.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import me.anisjamadar.newsapp_mvvm_architecture.data.repository.TopHeadlinesRepository
import me.anisjamadar.newsapp_mvvm_architecture.di.ActivityContext
import me.anisjamadar.newsapp_mvvm_architecture.ui.base.ViewModelProviderFactory
import me.anisjamadar.newsapp_mvvm_architecture.ui.top_headlines.TopHeadlineAdapter
import me.anisjamadar.newsapp_mvvm_architecture.ui.top_headlines.TopHeadlineViewModel

@Module
class ActivityModule(private val activity: AppCompatActivity) {
    @ActivityContext
    @Provides
    fun providesContext(): Context {
        return activity
    }

    @Provides
    fun providesTopHeadlineAdapter() = TopHeadlineAdapter(ArrayList())

    @Provides
    fun provideNewsListViewModel(topHeadlineRepository: TopHeadlinesRepository): TopHeadlineViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(TopHeadlineViewModel::class) {
                TopHeadlineViewModel(topHeadlineRepository)
            })[TopHeadlineViewModel::class.java]
    }

}