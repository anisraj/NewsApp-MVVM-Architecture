package me.anisjamadar.newsapp_mvvm_architecture.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import me.anisjamadar.newsapp_mvvm_architecture.di.ActivityContext
import me.anisjamadar.newsapp_mvvm_architecture.ui.top_headlines.TopHeadlineAdapter

@Module
class ActivityModule(private val activity: AppCompatActivity) {
    @ActivityContext
    @Provides
    fun providesContext(): Context {
        return activity
    }

    @Provides
    fun providesTopHeadlineAdapter() = TopHeadlineAdapter(ArrayList())

}