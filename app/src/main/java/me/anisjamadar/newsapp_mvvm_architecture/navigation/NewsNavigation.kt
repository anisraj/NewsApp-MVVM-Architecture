package me.anisjamadar.newsapp_mvvm_architecture.navigation

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.anisjamadar.newsapp_mvvm_architecture.main.MainScreen
import me.anisjamadar.presentation.top_headlines.TopHeadlineScreen
import me.anisjamadar.presentation.top_headlines_offline_first.TopHeadlineOfflineFirstScreen
import me.anisjamadar.presentation.top_headlines_pagination.TopHeadlineScreenByPagination

sealed class Route(val name: String) {
    data object Main : Route("main")
    data object TopHeadline : Route("top_headline")
    data object TopHeadlineByPagination : Route("top_headline_by_pagination")
    data object TopHeadlineOfflineFirst : Route("top_headline_offline_first")
}

@Composable
fun NewsNavHost() {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Route.Main.name
    ) {
        composable(route = Route.Main.name) {
            MainScreen(
                onTopHeadlineClick = {
                    navController.navigate(Route.TopHeadline.name)
                },
                onTopHeadlineByPaginationClick = {
                    navController.navigate(Route.TopHeadlineByPagination.name)
                },
                onTopHeadlineOfflineFirstClick = {
                    navController.navigate(Route.TopHeadlineOfflineFirst.name)
                }
            )
        }

        composable(route = Route.TopHeadline.name) {
            TopHeadlineScreen(
                onNewsClick = {
                    openChromeTab(context, it)
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = Route.TopHeadlineByPagination.name) {
            TopHeadlineScreenByPagination(
                onBackClick = {
                    navController.popBackStack()
                },
                onNewsClick = {
                    openChromeTab(context, it)
                }
            )
        }

        composable(route = Route.TopHeadlineOfflineFirst.name) {
            TopHeadlineOfflineFirstScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onNewsClick = {
                    openChromeTab(context, it)
                }
            )
        }
    }
}

fun openChromeTab(context: Context, url: String) {
    CustomTabsIntent
        .Builder()
        .build()
        .launchUrl(context, Uri.parse(url))
}