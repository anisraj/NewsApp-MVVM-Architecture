package me.anisjamadar.newsapp_mvvm_architecture.di.component

import dagger.Component
import me.anisjamadar.newsapp_mvvm_architecture.NewsApplication
import me.anisjamadar.newsapp_mvvm_architecture.data.repository.TopHeadlinesRepository
import me.anisjamadar.newsapp_mvvm_architecture.di.module.ApplicationModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: NewsApplication)

    fun getTopHeadlinesRepository(): TopHeadlinesRepository

}