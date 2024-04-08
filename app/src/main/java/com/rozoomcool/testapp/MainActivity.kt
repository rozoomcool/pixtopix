package com.rozoomcool.testapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.rozoomcool.testapp.ui.theme.TestappTheme
import com.rozoomcool.testapp.presentation.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("PermissionLaunchedDuringComposition")
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

//            val storagePermissionState = rememberPermissionState(
//                Manifest.permission.MANAGE_EXTERNAL_STORAGE
//            )
//            storagePermissionState.status.shouldShowRationale


            TestappTheme(
                darkTheme = true
            ) {



//                if (!storagePermissionState.status.isGranted) {
//                    storagePermissionState.launchPermissionRequest()
//                }

                MainScreen()
            }
        }
    }
}