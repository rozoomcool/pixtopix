package com.rozoomcool.testapp.presentation

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.view.WindowInsets.Side
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.rozoomcool.testapp.navigation.AppNavGraph
import com.rozoomcool.testapp.navigation.Screen
import com.rozoomcool.testapp.navigation.rememberNavigationState
import dagger.hilt.android.qualifiers.ApplicationContext

@Composable
fun MainScreen() {

    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { _: Map<String, @JvmSuppressWildcards Boolean> ->

        })


    SideEffect {
        launcher.launch(
            arrayOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission_group.STORAGE
            )
        )
    }

    val navigationState = rememberNavigationState()

    AppNavGraph(
        navigationState = navigationState
    )
}