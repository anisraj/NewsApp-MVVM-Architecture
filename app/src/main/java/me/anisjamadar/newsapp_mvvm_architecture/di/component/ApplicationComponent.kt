package me.anisjamadar.newsapp_mvvm_architecture.di.component

import dagger.BindsInstance
import dagger.Component
import me.anisjamadar.newsapp_mvvm_architecture.NewsApplication
import me.anisjamadar.newsapp_mvvm_architecture.data.repository.NewsRepository
import me.anisjamadar.newsapp_mvvm_architecture.di.module.ApplicationModule
import me.anisjamadar.newsapp_mvvm_architecture.di.module.RandomModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    RandomModule::class
])
interface ApplicationComponent {

    fun inject(application: NewsApplication)

    fun getNewsRepository(): NewsRepository

    fun getInt(): Int

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: NewsApplication): ApplicationComponent
    }

}