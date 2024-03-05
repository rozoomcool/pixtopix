package com.rozoomcool.testapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun AppNavGraph(
    navigationState: NavigationState,
    startProjectContent: @Composable () -> Unit
) {
    NavHost(
        navController = navigationState.navHostController,
        startDestination = Screen.StartProject.route
    ) {
        composable(
            route = Screen.StartProject.route
        ) {
            startProjectContent()
        }
    }
}