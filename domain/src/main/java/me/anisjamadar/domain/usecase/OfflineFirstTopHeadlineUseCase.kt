package me.anisjamadar.domain.usecase

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import me.anisjamadar.domain.model.Article
import me.anisjamadar.domain.repository.OfflineFirstNewsRepository
import javax.inject.Inject

@ViewModelScoped
class OfflineFirstTopHeadlineUseCase @Inject constructor(
    private val repository: OfflineFirstNewsRepository
) {
    private val _loadingTrigger = MutableStateFlow(false)
    val loadingTrigger: StateFlow<Boolean> = _loadingTrigger.asStateFlow()

    private val _errorTrigger = MutableSharedFlow<Throwable>()
    val errorTrigger: SharedFlow<Throwable> = _errorTrigger.asSharedFlow()

    fun flowOfArticles(): Flow<List<Article>> {
        return repository
            .flowOfArticles()
            .catch {
                _errorTrigger.emit(it)
            }
    }

    suspend fun updateArticles(country: String) {
        _loadingTrigger.value = true
        repository
            .updateArticles(country)
            .onFailure {
                _errorTrigger.emit(it)
            }
        _loadingTrigger.value = false
    }
}