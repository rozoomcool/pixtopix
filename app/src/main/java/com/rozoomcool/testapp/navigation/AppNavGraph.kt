package com.rozoomcool.testapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import com.rozoomcool.testapp.presentation.CreateProjectDialog
import com.rozoomcool.testapp.presentation.EditorScreen


@Composable
fun AppNavGraph(
    navigationState: NavigationState,
    startProjectContent: @Composable () -> Unit,
    createProjectDialogContent: @Composable () -> Unit
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

        composable(
            route = Screen.Editor.route
        ) {
            EditorScreen()
        }

        dialog(Screen.CreateProject.route) {
            createProjectDialogContent()
        }
    }
}