package me.anisjamadar.newsapp_mvvm_architecture.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import me.anisjamadar.newsapp_mvvm_architecture.navigation.NewsNavHost
import me.anisjamadar.newsapp_mvvm_architecture.theme.NewsAppTheme

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