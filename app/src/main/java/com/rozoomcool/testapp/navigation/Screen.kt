package com.rozoomcool.testapp.navigation

sealed class Screen(
    val route: String
) {

    data object StartProject: Screen(START_PROJECT)
    data object CreateProject: Screen(CREATE_PROJECT)
    data object Editor: Screen(EDITOR)

    data object EditorProject: Screen(EDITOR_PROJECT) {
        fun routeWithArgs(title: String, width: Int, height: Int): String {
            return "editor_project/{$title}/{$width}/{$height}"
        }
    }


    private companion object {
        const val START_PROJECT = "start_project"
        const val CREATE_PROJECT = "create_project"
        const val EDITOR = "editor"
        const val EDITOR_PROJECT = "editor_project/{title}/{width}/{height}"
    }
}