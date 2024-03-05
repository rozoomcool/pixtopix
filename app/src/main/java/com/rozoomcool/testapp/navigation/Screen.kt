package com.rozoomcool.testapp.navigation

sealed class Screen(
    val route: String
) {

    data object StartProject: Screen(START_PROJECT)
    data object CreateProject: Screen(CREATE_PROJECT)


    private companion object {
        const val START_PROJECT = "start_project"
        const val CREATE_PROJECT = "create_project"
    }
}