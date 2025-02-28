package me.anisjamadar.newsapp_mvvm_architecture.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import me.anisjamadar.newsapp_mvvm_architecture.di.ActivityContext
import me.anisjamadar.newsapp_mvvm_architecture.di.ActivityScope

@Module
class ActivityModule {
    @ActivityContext
    @ActivityScope
    @Provides
    fun providesContext(activity: AppCompatActivity): Context {
        return activity
    }
}