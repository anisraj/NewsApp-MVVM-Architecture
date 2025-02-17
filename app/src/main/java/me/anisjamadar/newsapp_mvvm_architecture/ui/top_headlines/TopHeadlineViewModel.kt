package me.anisjamadar.newsapp_mvvm_architecture.ui.top_headlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import me.anisjamadar.newsapp_mvvm_architecture.data.model.Article
import me.anisjamadar.newsapp_mvvm_architecture.data.repository.NewsRepository
import me.anisjamadar.newsapp_mvvm_architecture.data.repository.NewsRepositoryImpl
import me.anisjamadar.newsapp_mvvm_architecture.ui.base.UiState
import me.anisjamadar.newsapp_mvvm_architecture.utils.AppConstant.COUNTRY

class TopHeadlineViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            newsRepository.getTopHeadlines(COUNTRY)
                .catch {
                    _uiState.value = UiState.Error(it.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}