package com.example.newsnow.presentation.news_navigator

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsnow.R
import com.example.newsnow.domain.model.Article
import com.example.newsnow.presentation.Dimens.MEDIUM_PADDING2
import com.example.newsnow.presentation.home.HomeScreen
import com.example.newsnow.presentation.home.HomeViewModel
import com.example.newsnow.presentation.navigation.Route
import com.example.newsnow.presentation.news_navigator.components.BottomNavigation
import com.example.newsnow.presentation.news_navigator.components.NewsBottomNavigation

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewsNavigator() {

    val bottomNavigationItems = remember {
        listOf(
            BottomNavigation(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigation(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigation(icon = R.drawable.ic_bookmark, text = "Bookmark")
        )
    }

    val navController = rememberNavController()
    val backstackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    selectedItem = when (backstackState?.destination?.route) {
        Route.HomeScreen.route -> 0
        Route.SearchScreen.route -> 1
        Route.BookmarkScreen.route -> 2
        else -> 0
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NewsBottomNavigation(
                items = bottomNavigationItems,
                selectedItem = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTab(navController, Route.HomeScreen.route)
                        1 -> navigateToTab(navController, Route.SearchScreen.route)
                        2 -> navigateToTab(navController, Route.BookmarkScreen.route)
                    }
                })
        },
        content = {
            NavHost(
                navController = navController,
                startDestination = Route.HomeScreen.route,
                modifier = Modifier.padding(bottom = MEDIUM_PADDING2)
            ) {
                composable(route = Route.HomeScreen.route) {
                    val viewModel: HomeViewModel = hiltViewModel()
                    val articles = viewModel.news.collectAsLazyPagingItems()
                    HomeScreen(
                        articles = articles,
                        navigateToSearch = {
                            navigateToTab(navController, Route.SearchScreen.route)
                        },
                        navigateToDetails = { article ->
                            navigateToDetails(navController, article)
                        })
                }
            }
        }
    )
}

private fun navigateToTab(navController: NavHostController, string: String) {
    navController.navigate(string) {
        navController.graph.startDestinationRoute?.let { homeScreen ->
            popUpTo(homeScreen) { saveState = true }
        }
        restoreState = true
        launchSingleTop = true
    }
}

private fun navigateToDetails(navController: NavHostController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        route = Route.DetailsScreen.route,
    )
}
