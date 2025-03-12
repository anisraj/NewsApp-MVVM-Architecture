package me.anisjamadar.newsapp_mvvm_architecture.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import me.anisjamadar.newsapp_mvvm_architecture.R
import me.anisjamadar.newsapp_mvvm_architecture.ui.base.MyCustomTopAppBar

@Composable
fun MainScreen(
    onTopHeadlineClick: () -> Unit,
    onTopHeadlineByPaginationClick: () -> Unit
) {
    Scaffold(
        topBar = {
            MyCustomTopAppBar(title = {
                Text(text = "Home")
            })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                space = 16.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            Button(
                onClick = {
                    onTopHeadlineClick.invoke()
                }
            ) {
                Text(
                    text = stringResource(R.string.top_headlines)
                )
            }

            Button(
                onClick = {
                    onTopHeadlineByPaginationClick.invoke()
                }
            ) {
                Text(
                    text = stringResource(R.string.top_headlines_pagination)
                )
            }
        }
    }
}
