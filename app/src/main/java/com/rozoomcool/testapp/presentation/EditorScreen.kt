package com.rozoomcool.testapp.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.rozoomcool.testapp.model.Pixel
import com.rozoomcool.testapp.ui.compoents.EditorBottomBar
import com.rozoomcool.testapp.ui.compoents.EditorTopBar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


val rows = 32
val cols = 32
val fieldSize: Size = Size(cols.toFloat(), rows.toFloat())


@SuppressLint("MutableCollectionMutableState", "CoroutineCreationDuringComposition")
@Composable
fun EditorScreen() {
    val coroutineScope = rememberCoroutineScope()
    val plane = remember {
        mutableStateOf(setOf<Pixel>())
    }
    var scaleFactor by remember {
        mutableFloatStateOf(1f)
    }

//    coroutineScope.launch {
//        plane.value = generateEmptyPlane(rows, cols)
//    }

    Scaffold(
        topBar = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                EditorTopBar()
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(elevation = 4.dp)
                        .background(colorScheme.background)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("текущий слой")
                }
            }
        },
        bottomBar = {
            EditorBottomBar()
        }
    ) { paddingValues ->

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTransformGestures { _, _, zoom, _ ->
//                        Log.d("===", zoom.dp.toString())
                        coroutineScope.launch(Dispatchers.Default) {
                            scaleFactor *= zoom
                        }
                    }
                }
        ) {

            val pixelSize: Float = (LocalConfiguration.current.screenWidthDp / cols).toFloat()

            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((pixelSize * rows).dp)
                    .scale(scaleFactor)
                    .pointerInput(Unit) {
                        val localPixelSize: Float = (size.width / cols).toFloat()
                        detectTapGestures { onTap ->
                            coroutineScope.launch(Dispatchers.Default) {
                                val cordX =
                                    ((onTap.x - (onTap.x % localPixelSize)) / localPixelSize).toInt()
                                val cordY =
                                    ((onTap.y - (onTap.y % localPixelSize)) / localPixelSize).toInt()

                                if (cordX !in 0..<cols && cordY !in 0..<rows) {
                                    cancel()
                                }

//                                Log.d("===", "x: $cordX, Y: $cordY")

                                plane.value = addPixel(
                                    plane.value,
                                    cordX,
                                    cordY,
                                    0xFFFF0000
                                )
                            }
                        }
                    }
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDrag = { change, _ ->
                                coroutineScope.launch(Dispatchers.Default) {
                                    val cordX =
                                        ((change.position.x - (change.position.x % pixelSize)) / pixelSize).toInt()
                                    val cordY =
                                        ((change.position.y - (change.position.y % pixelSize)) / pixelSize).toInt()

                                    if (cordX !in 0..<cols && cordY !in 0..<rows) {
                                        cancel()
                                    }

//                                    Log.d("===", "x: $cordX, Y: $cordY")

                                    plane.value = addPixel(
                                        plane.value,
                                        cordX,
                                        cordY,
                                        0xFFFF0000
                                    )
                                }
                            }
                        )
                    }
            ) {
                val pixelSize: Float = this.size.width / cols

                val fScale = if (rows > 64 || cols > 64) 4 else 1

                for (i in 0 until rows / fScale) {
                    for (j in 0 until cols / fScale) {

                        drawIntoCanvas {
                            it.drawRect(
                                Rect(
                                    Offset(
                                    x = pixelSize * (j) * fScale,
                                    y = pixelSize * (i) * fScale
                                    ),
                                    Size(pixelSize * fScale, pixelSize * fScale)
                                ),
                                paint = Paint().apply {
                                    color = if ((i + j) % 2 == 0) Color.White else Color(0xFFAAAAAA)
                                }
                            )
                        }
                    }
                }

                if (plane.value.isNotEmpty()) {
                    plane.value.map { pixel ->
                        drawRect(
                            color = Color(pixel.color),
                            size = Size(
                                pixelSize,
                                pixelSize
                            ),
                            topLeft = Offset(
                                x = pixelSize * (pixel.x),
                                y = pixelSize * (pixel.y)
                            )
                        )
                    }
                }
            }
        }
    }
}

suspend fun generateEmptyPlane(rows: Int, cols: Int): List<Pixel> {
    return List(rows * cols) { index ->
        val x = index % cols
        val y = index / cols

        val emptyColor = if ((x + y) % 2 == 0) 0xFFFFFFFF else 0xFFAAAAAA

        Pixel(x, y, 0)
    }
}

fun addPixel(pixels: Set<Pixel>, x: Int, y: Int, color: Long): Set<Pixel> {
    val newList: MutableSet<Pixel> = pixels.toMutableSet()
    if (x in 0..<cols && y in 0..<rows) {
        newList.apply { add(Pixel(x, y, color)) }
    }
    Log.d("===", newList.size.toString())
    return newList
}