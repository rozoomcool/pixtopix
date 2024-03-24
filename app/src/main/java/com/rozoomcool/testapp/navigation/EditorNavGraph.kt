package com.rozoomcool.testapp.navigation

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navigation
import com.rozoomcool.testapp.domain.editorViewModel.EditorViewModel
import com.rozoomcool.testapp.presentation.createProject.CreateProjectDialog
import com.rozoomcool.testapp.presentation.editor.EditorScreen

fun NavGraphBuilder.editorNavGraph(
    navigationState: NavigationState
) {
    navigation(
        route = Screen.Editor.route,
        startDestination = Screen.CreateProject.route
    ) {

        dialog(Screen.CreateProject.route) {entry ->

            val editorViewModel = entry.sharedViewModel<EditorViewModel>(navController = navigationState.navHostController)

            CreateProjectDialog(
                navigationState = navigationState,
                onEditorEvent = editorViewModel::onEvent
            )
        }

        composable(
            route = Screen.EditorProject.route
        ) {entry ->

            val editorViewModel = entry.sharedViewModel<EditorViewModel>(navController = navigationState.navHostController)
            val editorState = editorViewModel.state.collectAsState().value

            EditorScreen(
                editorState = editorState,
                onEditorEvent = editorViewModel::onEvent,
                onBackButtonClick = {
                    navigationState.pop()
                }
            )
        }
    }
}