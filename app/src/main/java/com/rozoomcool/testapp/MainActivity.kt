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



//
//data class Pixel(val x: Int, val y: Int, val color: Color)
//
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun PixelArtEditor() {
//    var pixels by remember { mutableStateOf(generateEmptyPixels(32, 32)) }
//    var isDrawing by remember { mutableStateOf(false) }
//    var selectedColor by remember { mutableStateOf(Color.Black) }
//
//    var scaleFactor by remember { mutableStateOf(1f) }
//    var offset by remember { mutableStateOf(Offset(0f, 0f)) }
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = { Text("Pixel Art Editor") },
//                actions = {
//                    IconButton(onClick = { /* Handle save action */ }) {
//                        Icon(Icons.Default.Save, contentDescription = "Save")
//                    }
//                    IconButton(onClick = { /* Handle clear action */ }) {
//                        Icon(Icons.Default.Clear, contentDescription = "Clear")
//                    }
//                    IconButton(onClick = { /* Handle undo action */ }) {
//                        Icon(Icons.Default.Undo, contentDescription = "Undo")
//                    }
//                    IconButton(onClick = { /* Handle settings action */ }) {
//                        Icon(Icons.Default.Settings, contentDescription = "Settings")
//                    }
//                }
//            )
//        },
//        content = {
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .pointerInput(Unit) {
//                        detectTransformGestures { _, pan, zoom, _ ->
//                            // Handle pan and zoom gestures
//                            scaleFactor *= zoom
//                            offset = Offset(offset.x + pan.x, offset.y + pan.y)
//                        }
//                    }
//            ) {
//                Canvas(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(Color.White)
//                        .pointerInput(Unit) {
//                            detectTransformGestures { _, panChange, _, _ ->
//                                // Handle drawing pixels on pan change
//                                if (isDrawing) {
//                                    val x = (panChange.x + offset.x) / scaleFactor
//                                    val y = (panChange.y + offset.y) / scaleFactor
//                                    pixels = drawPixel(
//                                        pixels,
//                                        x.roundToInt(),
//                                        y.roundToInt(),
//                                        selectedColor
//                                    )
//                                }
//                            }
//                        }
//                ) {
//                    // Draw pixels on the canvas
//                    for (pixel in pixels) {
//                        drawRect(
//                            color = pixel.color,
//                            topLeft = Offset(pixel.x * scaleFactor, pixel.y * scaleFactor),
//                            size = Size(scaleFactor, scaleFactor)
//                        )
//                    }
//                }
//
//                // Draw color picker
//                Column(
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(16.dp),
//                    verticalArrangement = Arrangement.Bottom
//                ) {
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(8.dp),
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    ) {
//                        ColorButton(color = Color.Black, onColorSelected = { selectedColor = it })
//                        ColorButton(color = Color.Red, onColorSelected = { selectedColor = it })
//                        ColorButton(color = Color.Green, onColorSelected = { selectedColor = it })
//                        ColorButton(color = Color.Blue, onColorSelected = { selectedColor = it })
//                    }
//                }
//            }
//        }
//    )
//}
//
//@Composable
//fun ColorButton(color: Color, onColorSelected: (Color) -> Unit) {
//    IconButton(
//        onClick = { onColorSelected(color) },
//        modifier = Modifier.size(40.dp)
//    ) {
//        Box(
//            modifier = Modifier
//                .background(color = color, shape = CircleShape)
//                .size(32.dp)
//        )
//    }
//}
//
//fun generateEmptyPixels(rows: Int, cols: Int): List<Pixel> {
//    return List(rows * cols) { index ->
//        val x = index % cols
//        val y = index / cols
//        Pixel(x, y, Color.White)
//    }
//}
//
//fun drawPixel(pixels: List<Pixel>, x: Int, y: Int, color: Color): List<Pixel> {
//    return pixels.map {
//        if (it.x == x && it.y == y) {
//            Pixel(x, y, color)
//        } else {
//            it
//        }
//    }
//}
