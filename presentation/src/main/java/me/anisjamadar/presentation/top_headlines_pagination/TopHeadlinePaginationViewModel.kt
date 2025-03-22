package me.anisjamadar.presentation.top_headlines_pagination

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import me.anisjamadar.domain.usecase.TopHeadlineUseCase
import me.anisjamadar.util.AppConstant.COUNTRY
import javax.inject.Inject

@HiltViewModel
class TopHeadlinePaginationViewModel @Inject constructor(
    useCase: TopHeadlineUseCase
) : ViewModel() {

    val pagingDataFlow = useCase
        .getTopHeadlinesByPagination(COUNTRY)
        .cachedIn(viewModelScope)
}