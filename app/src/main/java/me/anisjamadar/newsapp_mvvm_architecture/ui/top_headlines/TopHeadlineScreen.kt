package me.anisjamadar.newsapp_mvvm_architecture.ui.top_headlines

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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import me.anisjamadar.newsapp_mvvm_architecture.R
import me.anisjamadar.newsapp_mvvm_architecture.data.model.Article
import me.anisjamadar.newsapp_mvvm_architecture.data.model.Source
import me.anisjamadar.newsapp_mvvm_architecture.ui.base.MyCustomTopAppBar
import me.anisjamadar.newsapp_mvvm_architecture.ui.base.ShowError
import me.anisjamadar.newsapp_mvvm_architecture.ui.base.ShowLoading
import me.anisjamadar.newsapp_mvvm_architecture.ui.base.UiState
import me.anisjamadar.newsapp_mvvm_architecture.ui.theme.onSecondaryLight
import me.anisjamadar.newsapp_mvvm_architecture.ui.theme.primaryLight
import me.anisjamadar.newsapp_mvvm_architecture.ui.theme.surfaceDark
import me.anisjamadar.newsapp_mvvm_architecture.ui.theme.surfaceLight

@Composable
fun TopHeadlineScreen(
    onNewsClick: (url: String) -> Unit
) {
    val viewModel = hiltViewModel<TopHeadlineViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            MyCustomTopAppBar(title = {
                Text(stringResource(R.string.top_headlines))
            })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
        ) {
            TopHeadlineScreenContent(
                uiState = uiState,
                onNewsClick = onNewsClick
            )
        }
    }
}

@Composable
fun TopHeadlineScreenContent(
    uiState: UiState<List<Article>>,
    onNewsClick: (url: String) -> Unit
) {
    when(uiState) {
        is UiState.Loading -> {
            ShowLoading()
        }
        is UiState.Error -> {
            ShowError(uiState.message)
        }
        is UiState.Success -> {
            ArticleList(uiState.data, onNewsClick)
        }
    }
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
            .height(100.dp)
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
        fontSize = 10.sp
    )
}

@Preview(showBackground = true)
@Composable
private fun ArticlePreview() {
    ArticleLayout(Article("India wins again", "Some demo description to get preview", "", "https://cdn.pixabay.com/photo/2012/10/26/09/42/climb-63281_1280.jpg", Source("", "BBC News")), {})
}