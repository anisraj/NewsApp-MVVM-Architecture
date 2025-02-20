package me.anisjamadar.newsapp_mvvm_architecture.ui.top_headlines

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.anisjamadar.newsapp_mvvm_architecture.data.model.Article
import me.anisjamadar.newsapp_mvvm_architecture.data.repository.NewsPagingSource
import me.anisjamadar.newsapp_mvvm_architecture.data.repository.NewsRepository
import me.anisjamadar.newsapp_mvvm_architecture.data.repository.NewsRepositoryImpl
import me.anisjamadar.newsapp_mvvm_architecture.ui.base.UiState
import me.anisjamadar.newsapp_mvvm_architecture.utils.AppConstant.COUNTRY
import javax.inject.Inject

class TopHeadlineViewModel @Inject constructor(
    private val pagingSource: NewsPagingSource
) : ViewModel() {

    val flow = Pager(
        PagingConfig(pageSize = 5)
    ) {
        pagingSource
    }.flow
        .cachedIn(viewModelScope)
}