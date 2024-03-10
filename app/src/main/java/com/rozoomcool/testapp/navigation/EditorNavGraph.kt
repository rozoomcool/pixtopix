package com.rozoomcool.testapp.navigation

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.rozoomcool.testapp.domain.editorViewModel.EditorEvent
import com.rozoomcool.testapp.domain.editorViewModel.EditorViewModel
import com.rozoomcool.testapp.presentation.CreateProjectDialog
import com.rozoomcool.testapp.presentation.EditorScreen

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

            Log.d("===", "editor ${editorViewModel.hashCode()} | ${editorState}")

            EditorScreen(
                editorState = editorState,
                onEditorEvent = editorViewModel::onEvent
            )
        }
    }
}