package me.anisjamadar.newsapp_mvvm_architecture.di.component

import androidx.appcompat.app.AppCompatActivity
import dagger.BindsInstance
import dagger.Subcomponent
import me.anisjamadar.newsapp_mvvm_architecture.ui.top_headlines_pagination.TopHeadlinesPaginationActivity
import me.anisjamadar.newsapp_mvvm_architecture.di.ActivityScope
import me.anisjamadar.newsapp_mvvm_architecture.di.module.ActivityModule

@ActivityScope
@Subcomponent(
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(activity: TopHeadlinesPaginationActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(
            @BindsInstance activity: AppCompatActivity
        ): ActivityComponent
    }
}