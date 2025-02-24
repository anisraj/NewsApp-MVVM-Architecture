package me.anisjamadar.newsapp_mvvm_architecture.ui.top_headlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import me.anisjamadar.newsapp_mvvm_architecture.data.repository.NewsRepository
import me.anisjamadar.newsapp_mvvm_architecture.utils.AppConstant.COUNTRY
import javax.inject.Inject

class TopHeadlineViewModel @Inject constructor(
    newsRepository: NewsRepository
) : ViewModel() {

    val pagingDataFlow = newsRepository
        .getTopHeadlines(COUNTRY)
        .cachedIn(viewModelScope)
}