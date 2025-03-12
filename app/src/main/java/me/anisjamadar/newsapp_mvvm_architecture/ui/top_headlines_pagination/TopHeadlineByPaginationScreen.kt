package me.anisjamadar.newsapp_mvvm_architecture.ui.top_headlines_pagination

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import me.anisjamadar.newsapp_mvvm_architecture.R
import me.anisjamadar.newsapp_mvvm_architecture.ui.base.MyCustomTopAppBar

@Composable
fun TopHeadlineScreenByPagination() {
    Scaffold(
        topBar = {
            MyCustomTopAppBar(title = {
                Text(stringResource(R.string.top_headlines_pagination))
            })
        }
    ) { padding ->
    }
}