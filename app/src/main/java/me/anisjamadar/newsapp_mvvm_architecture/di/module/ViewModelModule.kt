package me.anisjamadar.newsapp_mvvm_architecture.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import me.anisjamadar.newsapp_mvvm_architecture.di.ViewModelKey
import me.anisjamadar.newsapp_mvvm_architecture.ui.base.ViewModelFactory
import me.anisjamadar.newsapp_mvvm_architecture.ui.top_headlines_pagination.TopHeadlinePaginationViewModel

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TopHeadlinePaginationViewModel::class)
    fun bindTopHeadlineViewModel(topHeadlineViewModel: TopHeadlinePaginationViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}