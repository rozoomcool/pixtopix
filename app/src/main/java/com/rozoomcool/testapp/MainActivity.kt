package com.rozoomcool.testapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.core.content.ContextCompat
import com.rozoomcool.testapp.ui.theme.TestappTheme
import com.rozoomcool.testapp.presentation.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.RequestPermission(),
                onResult = { isGranted: Boolean ->
                    if (isGranted) {
                        Log.d("ExampleScreen", "PERMISSION GRANTED")

                    } else {
                        Log.d("ExampleScreen", "PERMISSION DENIED")
                    }
                })

            TestappTheme(
                darkTheme = true
            ) {

                when (PackageManager.PERMISSION_GRANTED) {
                    ContextCompat.checkSelfPermission(
                        applicationContext,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ) -> {
                        Log.d("ExampleScreen", "Code requires permission")
                    }

                    else -> {
                        LaunchedEffect(key1 = true) {
                            launcher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        }
                    }
                }
                MainScreen()
            }
        }
    }
}