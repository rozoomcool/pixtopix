package com.rozoomcool.testapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rozoomcool.testapp.navigation.AppNavGraph
import com.rozoomcool.testapp.navigation.rememberNavigationState
import com.rozoomcool.testapp.ui.animations.StarsAnimation

@Composable
fun MainScreen() {
    AppNavGraph(
        navigationState = rememberNavigationState(),
        startProjectContent = { StartProjectScreen() }
    )
}