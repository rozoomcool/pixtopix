package com.rozoomcool.testapp.presentation

import androidx.compose.runtime.Composable
import com.rozoomcool.testapp.navigation.AppNavGraph
import com.rozoomcool.testapp.navigation.Screen
import com.rozoomcool.testapp.navigation.rememberNavigationState

@Composable
fun MainScreen() {

    val navigationState = rememberNavigationState()

    AppNavGraph(
        navigationState = navigationState
    )
}