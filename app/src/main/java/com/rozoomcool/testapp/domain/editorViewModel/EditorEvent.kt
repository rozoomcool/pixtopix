package com.rozoomcool.testapp.domain.editorViewModel

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize
import com.rozoomcool.testapp.model.EditorTool

sealed class EditorEvent {
    data class Create(
        val title: String,
        val width: Int,
        val height: Int
    ): EditorEvent()

    data class TapPixel(
        val x: Float,
        val y: Float,
        val size: IntSize
    ): EditorEvent()

    data class PanLine(
        val start: Offset,
        val end: Offset,
        val size: IntSize
    ): EditorEvent()

    data class ChangeTool(
        val editorTool: EditorTool
    ): EditorEvent()
}