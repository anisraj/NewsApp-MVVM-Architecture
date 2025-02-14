package me.anisjamadar.newsapp_mvvm_architecture

import android.app.Application
import me.anisjamadar.newsapp_mvvm_architecture.di.component.ApplicationComponent
import me.anisjamadar.newsapp_mvvm_architecture.di.component.DaggerApplicationComponent
import me.anisjamadar.newsapp_mvvm_architecture.di.module.ApplicationModule

class NewsApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
            .apply {
                inject(this@NewsApplication)
            }

    }
}