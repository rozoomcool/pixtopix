package com.rozoomcool.testapp.domain.editorViewModel

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.IntSize

sealed class EditorEvent {
    data class Create(
        val title: String,
        val width: Int,
        val height: Int
    ): EditorEvent()

    data class DrawPixel(
        val x: Float,
        val y: Float,
        val size: IntSize
    ): EditorEvent()

    data class DrawLine(
        val start: Offset,
        val end: Offset,
        val size: IntSize
    ): EditorEvent()
}