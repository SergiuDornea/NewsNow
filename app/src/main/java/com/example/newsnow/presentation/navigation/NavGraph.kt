package com.example.newsnow.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.newsnow.presentation.onboarding.OnBoardingScreen
import com.example.newsnow.presentation.onboarding.OnBoardingViewModel
import com.example.newsnow.presentation.search.SearchScreen
import com.example.newsnow.presentation.search.SearchViewModel
import com.example.newsnow.ui.theme.NewsNowTheme

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        navigation(
            startDestination = Route.OnBoardingScreen.route,
            route = Route.AppStartNavigation.route
        ) {
            composable(route = Route.OnBoardingScreen.route) {
                NewsNowTheme {
                    val viewModel: OnBoardingViewModel = hiltViewModel()
                    OnBoardingScreen(event = viewModel::onEvent)
                }
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.HomeScreen.route
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                SearchScreen(
                    state = viewModel.state.value,
                    event = viewModel::onEvent,
                    navigate = {})
            }
            composable(route = Route.SearchScreen.route) {

            }
            composable(route = Route.BookmarkScreen.route) {

            }
            composable(route = Route.DetailsScreen.route) {

            }
        }
    }
}
