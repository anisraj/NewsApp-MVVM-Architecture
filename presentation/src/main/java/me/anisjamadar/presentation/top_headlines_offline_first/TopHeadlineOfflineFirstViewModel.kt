package me.anisjamadar.presentation.top_headlines_offline_first

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import me.anisjamadar.domain.usecase.OfflineFirstTopHeadlineUseCase
import me.anisjamadar.util.AppConstant.COUNTRY
import javax.inject.Inject

@HiltViewModel
class TopHeadlineOfflineFirstViewModel @Inject constructor(
    private val useCase: OfflineFirstTopHeadlineUseCase
) : ViewModel() {

    val newsFlow = useCase.flowOfArticles().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    val loadingSignal = useCase.loadingTrigger
    val errorSignal = useCase.errorTrigger

    fun updateData() {
        viewModelScope.launch {
            useCase.updateArticles(COUNTRY)
        }
    }
}