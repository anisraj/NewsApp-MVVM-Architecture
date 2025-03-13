package me.anisjamadar.newsapp_mvvm_architecture.ui.top_headlines_pagination

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import me.anisjamadar.newsapp_mvvm_architecture.R
import me.anisjamadar.newsapp_mvvm_architecture.data.model.Article
import me.anisjamadar.newsapp_mvvm_architecture.ui.base.ShowError
import me.anisjamadar.newsapp_mvvm_architecture.ui.base.ShowLoading
import me.anisjamadar.newsapp_mvvm_architecture.ui.base.UiState
import me.anisjamadar.newsapp_mvvm_architecture.ui.theme.primaryLight
import me.anisjamadar.newsapp_mvvm_architecture.ui.top_headlines.ArticleLayout

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopHeadlineScreenByPagination(
    onBackClick: () -> Unit,
    onNewsClick: (url: String) -> Unit
) {
    val viewModel = hiltViewModel<TopHeadlinePaginationViewModel>()
    val lazyPagingItems = viewModel.pagingDataFlow.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = primaryLight,
                    titleContentColor = Color.White
                ),
                title = {
                    Text(stringResource(R.string.top_headlines_pagination))
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onBackClick.invoke()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back button",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
        ) {
            TopHeadlineScreenByPaginationContent(
                lazyPagingItems = lazyPagingItems,
                onNewsClick = onNewsClick
            )
        }
    }
}

@Composable
fun TopHeadlineScreenByPaginationContent(
    lazyPagingItems: LazyPagingItems<Article>,
    onNewsClick: (url: String) -> Unit
) {
    when (lazyPagingItems.loadState.refresh) {
        is LoadState.Loading -> {
            ShowLoading()
        }

        is LoadState.Error -> {
            val error = lazyPagingItems.loadState.refresh as LoadState.Error
            ShowError(error.error.localizedMessage ?: "Error")
        }

        else -> {
            ArticleList(lazyPagingItems, onNewsClick)
        }
    }
}

@Composable
fun ArticleList(
    lazyPagingItems: LazyPagingItems<Article>,
    onNewsClick: (url: String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(
            space = 10.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        items(
            count = lazyPagingItems.itemCount,
            key = lazyPagingItems.itemKey { article -> article.url }
        ) { index ->
            val item = lazyPagingItems[index]
            if (item != null) {
                ArticleLayout(item, onNewsClick)
            }
        }
    }
}