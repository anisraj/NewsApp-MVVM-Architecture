package me.anisjamadar.presentation.top_headlines_pagination

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import me.anisjamadar.domain.repository.NewsRepository
import me.anisjamadar.util.AppConstant.COUNTRY
import javax.inject.Inject

@HiltViewModel
class TopHeadlinePaginationViewModel @Inject constructor(
    newsRepository: NewsRepository
) : ViewModel() {

    val pagingDataFlow = newsRepository
        .getTopHeadlinesByPagination(COUNTRY)
        .cachedIn(viewModelScope)
}