package com.rozoomcool.testapp.presentation.editor

import android.graphics.Bitmap
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.core.graphics.set
import com.rozoomcool.testapp.domain.editorViewModel.EditorEvent
import com.rozoomcool.testapp.model.Pixel

@Composable
fun CanvasField(
    cols: Int,
    rows: Int,
    pixels: Set<Pixel>,
    scaleFactor: Float,
    offsetFactor: Offset,
    onEditorEvent: (EditorEvent) -> Unit,
    pixelSize: Float,
    zIndex: Float,
    isSelectable: Boolean,
    isDrawable: Boolean
) {

    val width = LocalView.current.width

    val backgroundBitmap by remember {
        mutableStateOf(
            getBackGroundBitmap(rows, cols, width)
        )
    }

    Canvas(
        modifier = Modifier
            .zIndex(1f)
            .fillMaxWidth()
            .height((pixelSize * rows).dp)
            .scale(scaleFactor)
            .offset(offsetFactor.x.dp, offsetFactor.y.dp)
            .pointerInput(isDrawable) {
                if (isDrawable) {
                    detectTapGestures { onTap ->
                        onEditorEvent(EditorEvent.TapPixel(onTap.x, onTap.y, size))
                    }
                }
            }
            .pointerInput(isDrawable) {
                if (isDrawable) {
                    detectDragGestures(
                        onDrag = { change, dragAmount ->
                            onEditorEvent(
                                EditorEvent.PanLine(
                                    start = change.position - dragAmount,
                                    end = change.position,
                                    size
                                )
                            )
                        },
                        onDragStart = {
                            onEditorEvent(EditorEvent.ActionStart)
                        }
                    )
                }
            }
    ) {
        val pixelSize: Float = this.size.width / cols

        drawBackgroundLines(cols, rows, pixelSize)


//        drawImage(
//            image = backgroundBitmap,
//            dstSize = IntSize(
//                (size.width * scaleFactor).toInt(),
//                (size.height.toInt() * scaleFactor).toInt()
//            )
//        )


        if (pixels.isNotEmpty()) {
            pixels.map { pixel ->
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

private fun getBackGroundBitmap(
    width: Int,
    height: Int,
    canvasWidth: Int
): ImageBitmap {
    val bitmap = Bitmap.createBitmap(
        ((canvasWidth / width) * width).toInt(),
        ((canvasWidth / width) * height).toInt(),
        Bitmap.Config.RGB_565
    )

    for (i in 0 until width) {
        for (j in 0 until height) {
            bitmap.setPixel(
                i,
                j,
                if ((i + j) % 2 == 0) Color.White.hashCode() else Color(0xFFAAAAAA).hashCode()
            )
        }
    }

    return bitmap.asImageBitmap()
}

private fun DrawScope.drawBackground(cols: Int, rows: Int, pixelSize: Float) {

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
}

private fun DrawScope.drawBackgroundLines(cols: Int, rows: Int, pixelSize: Float) {
    val fScale = if (rows > 64 || cols > 64) 4 else 1

    for (i in 0 until rows / fScale) {
        drawLine(
            color = Color(0xFF3D3D3D),
            start = Offset(0f, pixelSize * i),
            end = Offset(pixelSize * cols, pixelSize * i)
        )
    }

    for (j in 0 until cols / fScale) {

        drawLine(
            color = Color(0xFF3D3D3D),
            start = Offset(pixelSize * cols, 0f),
            end = Offset(0f, pixelSize * cols)
        )
    }
}

private fun DrawScope.drawModifiedPixels(pixels: Set<Pixel>, pixelSize: Float) {
    if (pixels.isNotEmpty()) {
        pixels.forEach { pixel ->
            drawRect(
                color = Color(pixel.color),
                topLeft = Offset(x = pixelSize * pixel.x, y = pixelSize * pixel.y),
                size = Size(pixelSize, pixelSize)
            )
        }
    }
}