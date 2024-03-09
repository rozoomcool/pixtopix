package com.rozoomcool.testapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import com.rozoomcool.testapp.domain.editorViewModel.EditorViewModel
import com.rozoomcool.testapp.presentation.CreateProjectDialog
import com.rozoomcool.testapp.presentation.EditorScreen
import com.rozoomcool.testapp.presentation.StartProjectScreen
import org.koin.androidx.compose.getViewModel


@Composable
fun AppNavGraph(
    navigationState: NavigationState,
//    startProjectContent: @Composable () -> Unit,
//    createProjectDialogContent: @Composable () -> Unit
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

        composable(
            route = Screen.Editor.route
        ) {
            EditorScreen()
        }

        dialog(Screen.CreateProject.route) {

            val editorViewModel = getViewModel<EditorViewModel>()
            val state = editorViewModel.state.collectAsState()
            val onEditorEvent = editorViewModel::onEvent

            CreateProjectDialog(
                editorState = state.value,
                onEditorEvent = onEditorEvent,
                onDismissRequest = { navigationState.pop() },
                onCreateButtonClickListener = { navigationState.navigateTo(Screen.Editor.route) }
            )
        }
    }
}