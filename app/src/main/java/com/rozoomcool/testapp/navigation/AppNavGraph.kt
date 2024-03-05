package com.rozoomcool.testapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import com.rozoomcool.testapp.presentation.CreateProjectDialog


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

        dialog(Screen.CreateProject.route) {
            CreateProjectDialog(
                onDismissRequest = { /*TODO*/ },
                onConfirmation = { /*TODO*/ }
            )
        }
    }
}