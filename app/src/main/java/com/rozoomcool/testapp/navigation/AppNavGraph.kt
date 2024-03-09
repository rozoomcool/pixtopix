package com.rozoomcool.testapp.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rozoomcool.testapp.presentation.StartProjectScreen


@SuppressLint("RestrictedApi")
@Composable
fun AppNavGraph(
    navigationState: NavigationState
) {
    NavHost(
        navController = navigationState.navHostController,
        startDestination = Screen.StartProject.route
    ) {
        composable(
            route = Screen.StartProject.route
        ) {
            StartProjectScreen(
                onCreateProjectClickListener = {
                    navigationState.navigateTo(Screen.CreateProject.route)
                },
                onOpenProjectClickListener = {}
            )
        }

        editorNavGraph(navigationState)
    }
}