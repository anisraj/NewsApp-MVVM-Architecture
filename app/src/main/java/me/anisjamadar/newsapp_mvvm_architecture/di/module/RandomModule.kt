package me.anisjamadar.newsapp_mvvm_architecture.di.module

import dagger.Module
import dagger.Provides
import kotlin.random.Random

@Module
object RandomModule {

    @Provides
    fun provideRandomInt(): Int {
        return Random.nextInt()
    }
}