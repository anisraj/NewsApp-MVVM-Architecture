package me.anisjamadar.newsapp_mvvm_architecture.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import me.anisjamadar.newsapp_mvvm_architecture.ui.theme.primaryLight
import me.anisjamadar.presentation.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onTopHeadlineClick: () -> Unit,
    onTopHeadlineByPaginationClick: () -> Unit,
    onTopHeadlineOfflineFirstClick: () -> Unit,
    onSearchTopHeadlineClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = primaryLight,
                    titleContentColor = Color.White
                ),
                title = {
                    Text("Home")
                }
            )
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

            Button(
                onClick = {
                    onTopHeadlineOfflineFirstClick.invoke()
                }
            ) {
                Text(
                    text = stringResource(R.string.top_headlines_offline_first)
                )
            }

            Button(
                onClick = {
                    onSearchTopHeadlineClick.invoke()
                }
            ) {
                Text(
                    text = stringResource(R.string.search_top_headlines)
                )
            }
        }
    }
}