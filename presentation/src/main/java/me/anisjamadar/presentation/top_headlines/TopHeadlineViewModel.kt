package me.anisjamadar.presentation.top_headlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import me.anisjamadar.domain.model.Article
import me.anisjamadar.domain.usecase.TopHeadlineUseCase
import me.anisjamadar.presentation.base.UiState
import me.anisjamadar.util.AppConstant.COUNTRY
import javax.inject.Inject

@HiltViewModel
class TopHeadlineViewModel @Inject constructor(
    private val useCase: TopHeadlineUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            useCase.getTopHeadlines(COUNTRY)
                .onFailure {
                    _uiState.value = UiState.Error(it.toString())
                }.onSuccess {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}