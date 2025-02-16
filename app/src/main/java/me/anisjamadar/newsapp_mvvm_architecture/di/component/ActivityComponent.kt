package me.anisjamadar.newsapp_mvvm_architecture.di.component

import dagger.Component
import me.anisjamadar.newsapp_mvvm_architecture.TopHeadlinesActivity
import me.anisjamadar.newsapp_mvvm_architecture.di.ActivityScope
import me.anisjamadar.newsapp_mvvm_architecture.di.module.ActivityModule

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(activity: TopHeadlinesActivity)
}