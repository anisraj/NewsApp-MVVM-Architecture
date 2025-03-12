package me.anisjamadar.newsapp_mvvm_architecture.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import me.anisjamadar.newsapp_mvvm_architecture.ui.base.NewsNavHost
import me.anisjamadar.newsapp_mvvm_architecture.ui.theme.NewsAppTheme

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