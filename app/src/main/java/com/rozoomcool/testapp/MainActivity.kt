package com.rozoomcool.testapp

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.rozoomcool.testapp.ui.theme.TestappTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.rozoomcool.testapp.presentation.MainScreen
import com.rozoomcool.testapp.ui.animations.StarsAnimation
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestappTheme(
                darkTheme = true
            ) {
                MainScreen()
            }
        }
    }
}