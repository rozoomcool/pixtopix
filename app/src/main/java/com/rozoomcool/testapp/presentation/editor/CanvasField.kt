package com.rozoomcool.testapp.presentation.editor

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
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
                        onDragEnd = {
                            onEditorEvent(EditorEvent.ActionEnd)
                        }
                    )
                }
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