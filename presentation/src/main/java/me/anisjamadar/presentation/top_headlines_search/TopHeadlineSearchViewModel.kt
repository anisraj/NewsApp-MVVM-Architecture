package me.anisjamadar.presentation.top_headlines_search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import me.anisjamadar.domain.model.Article
import me.anisjamadar.domain.usecase.SearchTopHeadlineUseCase
import me.anisjamadar.presentation.base.UiState
import me.anisjamadar.util.AppConstant.COUNTRY
import javax.inject.Inject

@HiltViewModel
class TopHeadlineSearchViewModel @Inject constructor(
    private val useCase: SearchTopHeadlineUseCase
) : ViewModel() {

    private val _query = MutableStateFlow("US news")

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchNews()
    }

    fun onQueryChange(query: String) {
        _query.value = query
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun fetchNews() {
        viewModelScope.launch {
            _query
                .debounce(300)
                .filter { query ->
                    return@filter query.isNotEmpty()
                }
                .distinctUntilChanged()
                .flatMapLatest {
                    useCase.getTopHeadlines(COUNTRY, it)
                }
                .flowOn(Dispatchers.IO)
                .catch {
                    _uiState.value = UiState.Error(it.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}