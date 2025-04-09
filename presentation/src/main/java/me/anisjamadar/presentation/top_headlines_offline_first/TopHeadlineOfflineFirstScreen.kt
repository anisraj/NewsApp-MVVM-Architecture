package me.anisjamadar.presentation.top_headlines_offline_first

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import me.anisjamadar.domain.model.Article
import me.anisjamadar.domain.model.Source
import me.anisjamadar.newsapp_mvvm_architecture.ui.theme.onSecondaryLight
import me.anisjamadar.newsapp_mvvm_architecture.ui.theme.primaryLight
import me.anisjamadar.newsapp_mvvm_architecture.ui.theme.surfaceDark
import me.anisjamadar.newsapp_mvvm_architecture.ui.theme.surfaceLight
import me.anisjamadar.presentation.R
import me.anisjamadar.presentation.base.ShowError
import me.anisjamadar.presentation.base.ShowLoading
import me.anisjamadar.presentation.base.ShowLoadingBar
import me.anisjamadar.presentation.base.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopHeadlineOfflineFirstScreen(
    onNewsClick: (url: String) -> Unit,
    onBackClick: () -> Unit,
) {
    val viewModel = hiltViewModel<TopHeadlineOfflineFirstViewModel>()
    val newsList by viewModel.newsFlow.collectAsStateWithLifecycle()
    val loadingSignal by viewModel.loadingSignal.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.updateData()
    }

    val retryLabel = stringResource(R.string.retry)
    LaunchedEffect(Unit) {
        viewModel.errorSignal.collect {
            val snackBarResult = snackBarHostState.showSnackbar(
                message = it.localizedMessage ?: "Some Error",
                actionLabel = retryLabel
            )
            if (snackBarResult == SnackbarResult.ActionPerformed) {
                viewModel.updateData()
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = primaryLight,
                    titleContentColor = Color.White
                ),
                title = {
                    Text(stringResource(R.string.top_headlines_offline_first))
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
            if (loadingSignal) {
                ShowLoadingBar()
            }
            TopHeadlineScreenContent(
                newsData = newsList,
                onNewsClick = onNewsClick
            )
        }
    }
}

@Composable
fun TopHeadlineScreenContent(
    newsData: List<Article>,
    onNewsClick: (url: String) -> Unit
) {
    ArticleList(newsData, onNewsClick)
}

@Composable
fun ArticleList(articles: List<Article>, onNewsClick: (url: String) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(
            space = 10.dp,
            alignment = Alignment.CenterVertically
        )
    ) {
        items(articles, key = { article -> article.url }) { item ->
            ArticleLayout(item, onNewsClick)
        }
    }
}

@Composable
fun ArticleLayout(article: Article, onNewsClick: (url: String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(border = BorderStroke(2.dp, surfaceLight), RoundedCornerShape(5.dp))
            .clickable {
                if (article.url.isNotEmpty()) {
                    onNewsClick(article.url)
                }
            },
        verticalArrangement = Arrangement.Center
    ) {
        NewsTitle(article)
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                space = 5.dp,
                alignment = Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Color(0xFFF4F5FD))
                .padding(10.dp)
                .clip(RoundedCornerShape(5.dp))
                .border(border = BorderStroke(1.dp, Color(0xFFF4F5FD)))
        ) {
            BannerImage(article)
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(
                    space = 5.dp,
                    alignment = Alignment.CenterVertically
                )
            ) {
                NewsDescription(article)
                NewsSource(article)
            }
        }
    }
}

@Composable
fun NewsTitle(article: Article) {
    Text(
        text = article.title ?: "N/A",
        modifier = Modifier
            .fillMaxWidth()
            .background(surfaceLight)
            .padding(10.dp),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        fontSize = 17.sp,
        color = primaryLight
    )
}

@Composable
fun BannerImage(article: Article) {
    AsyncImage(
        model = article.imageUrl,
        contentDescription = article.title,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth(0.5f)
    )
}

@Composable
fun NewsDescription(article: Article) {
    Text(
        text = article.description ?: "N/A",
        color = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .border(border = BorderStroke(1.dp, Color.White))
            .background(surfaceDark)
            .padding(16.dp),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        fontSize = 14.sp
    )
}

@Composable
fun NewsSource(article: Article) {
    Text(
        text = article.source?.name ?: "N/A",
        color = onSecondaryLight,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(2.dp))
            .border(BorderStroke(2.dp, Color(0XFFEACEF7)))
            .background(Color(0XFFEACEF7))
            .padding(6.dp),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontSize = 10.sp
    )
}
