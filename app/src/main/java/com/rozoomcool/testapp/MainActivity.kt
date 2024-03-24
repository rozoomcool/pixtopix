package com.rozoomcool.testapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.rozoomcool.testapp.ui.theme.TestappTheme
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import com.rozoomcool.testapp.presentation.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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