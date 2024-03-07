package com.rozoomcool.testapp.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.rozoomcool.testapp.model.Pixel
import com.rozoomcool.testapp.ui.compoents.EditorBottomBar
import com.rozoomcool.testapp.ui.compoents.EditorTopBar


@SuppressLint("MutableCollectionMutableState")
@Composable
fun EditorScreen() {
    Scaffold(
        topBar = {
            EditorTopBar()
        },
        bottomBar = {
            EditorBottomBar()
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 4.dp)
                    .background(colorScheme.background)
                    .padding(8.dp)
                    .align(Alignment.TopCenter),
                contentAlignment = Alignment.Center
            ) {
                Text("текущий слой")
            }

            val plane by remember {
                mutableStateOf(generateEmptyPlane())
            }

            Spacer(modifier = Modifier.height(46.dp))

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectTransformGestures { centroid, pan, zoom, rotation ->
                            Log.d("===", pan.x.toString())
                        }
                    }
            ) {
                Canvas(modifier = Modifier
                    .fillMaxSize()) {
                    repeat(plane.size) { it ->
                        repeat(plane[it].size) { ij ->
                            val pC = if ((it + ij) % 2 == 0) 0xFFFFFFFF else 0xFFDDDDDD
                                drawRect(
                                    color = Color(plane[it][ij]?.color ?: pC),
                                    size = Size(
                                        this.size.width / plane.size,
                                        this.size.width / plane.size
                                    ),
                                    topLeft = Offset(
                                        x = this.size.width / plane.size * (ij),
                                        y = this.size.width / plane.size * (it)
                                    )
                                )
                        }
                    }
                }
            }

        }
    }
}

fun generateEmptyPlane(): MutableList<MutableList<Pixel?>> {
    return MutableList(
        size = 32
    ) { _ -> MutableList<Pixel?>(size = 32) { _ -> null } }
}