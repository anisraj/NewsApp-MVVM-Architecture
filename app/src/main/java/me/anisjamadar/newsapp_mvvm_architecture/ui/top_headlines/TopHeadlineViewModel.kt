package me.anisjamadar.newsapp_mvvm_architecture.ui.top_headlines

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.anisjamadar.newsapp_mvvm_architecture.data.model.Article
import me.anisjamadar.newsapp_mvvm_architecture.data.repository.NewsRepository
import me.anisjamadar.newsapp_mvvm_architecture.ui.base.UiState
import me.anisjamadar.newsapp_mvvm_architecture.utils.AppConstant.COUNTRY
import javax.inject.Inject

class TopHeadlineViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<PagingData<Article>>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            newsRepository
                .getTopHeadlines(COUNTRY)
                .catch { e ->
                    Log.d("TAG", "got error: ${e.toString()}")
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    Log.d("TAG", "got data: ${it.toString()}")
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}