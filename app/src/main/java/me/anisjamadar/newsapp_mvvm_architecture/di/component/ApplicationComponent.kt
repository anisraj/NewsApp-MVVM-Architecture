package me.anisjamadar.newsapp_mvvm_architecture.di.component

import dagger.BindsInstance
import dagger.Component
import me.anisjamadar.newsapp_mvvm_architecture.NewsApplication
import me.anisjamadar.newsapp_mvvm_architecture.data.repository.NewsRepository
import me.anisjamadar.newsapp_mvvm_architecture.di.ApplicationScope
import me.anisjamadar.newsapp_mvvm_architecture.di.module.ApplicationModule

@ApplicationScope
@Component(modules = [
    ApplicationModule::class
])
interface ApplicationComponent {

    fun inject(application: NewsApplication)

    fun getNewsRepository(): NewsRepository

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: NewsApplication): ApplicationComponent
    }

}