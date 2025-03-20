package me.anisjamadar.newsapp_mvvm_architecture.ui.top_headlines

import app.cash.turbine.test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import me.anisjamadar.newsapp_mvvm_architecture.data.model.Article
import me.anisjamadar.newsapp_mvvm_architecture.data.repository.NewsRepository
import me.anisjamadar.newsapp_mvvm_architecture.ui.base.UiState
import me.anisjamadar.newsapp_mvvm_architecture.utils.AppConstant.COUNTRY
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class TopHeadlineViewModelTest {

    @Mock
    private lateinit var newsRepository: NewsRepository

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun fetchNews_whenRepositoryResponseSuccess_shouldSetSuccessUiState() {
        runTest {
            given(newsRepository.getTopHeadlines(COUNTRY)).willReturn(Result.success(emptyList()))
            val viewModel = TopHeadlineViewModel(newsRepository)
            viewModel.uiState.test {
                assertEquals(UiState.Success(emptyList<Article>()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(newsRepository, times(1)).getTopHeadlines(COUNTRY)
        }
    }

    @Test
    fun fetchNews_whenRepositoryResponseError_shouldSetErrorState() {
        runTest {
            val errorMessage = "Error occurred"
            given(newsRepository.getTopHeadlines(COUNTRY)).willReturn(Result.failure(IllegalStateException(errorMessage)))
            val viewModel = TopHeadlineViewModel(newsRepository)
            viewModel.uiState.test {
                assertEquals(UiState.Error(IllegalStateException(errorMessage).toString()), awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
            verify(newsRepository, times(1)).getTopHeadlines(COUNTRY)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}