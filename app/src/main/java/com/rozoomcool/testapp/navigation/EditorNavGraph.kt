package com.rozoomcool.testapp.navigation

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
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

        dialog(Screen.CreateProject.route) {

            CreateProjectDialog(
                navigationState = navigationState
            )
        }

        composable(
            route = Screen.EditorProject.route
        ) {entry ->

            val editorViewModel = hiltViewModel<EditorViewModel>()
            val editorState = editorViewModel.state.collectAsState().value

            val title: String = entry.arguments?.getString("title") ?: ""
            val width: Int = entry.arguments?.getInt("width") ?: 1
            val height: Int = entry.arguments?.getInt("height") ?: 1

            editorViewModel.onEvent(
                EditorEvent.Create(
                    title = title,
                    width = width,
                    height = height
                )
            )

            Log.d("===", "editor ${editorViewModel.hashCode()} | ${editorState}")

            EditorScreen(
                editorState = editorState,
                onEditorEvent = editorViewModel::onEvent
            )
        }
    }
}