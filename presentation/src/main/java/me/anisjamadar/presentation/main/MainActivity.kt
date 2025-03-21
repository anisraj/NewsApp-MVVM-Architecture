package me.anisjamadar.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import me.anisjamadar.newsapp_mvvm_architecture.ui.theme.NewsAppTheme
import me.anisjamadar.presentation.base.NewsNavHost

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                NewsNavHost()
            }
        }
    }
}