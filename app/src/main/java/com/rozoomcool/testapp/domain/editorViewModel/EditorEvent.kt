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
        val width: Float
    ): EditorEvent()

    data class PanLine(
        val start: Offset,
        val end: Offset,
        val width: Float
    ): EditorEvent()

    data class ChangeTool(
        val editorTool: EditorTool
    ): EditorEvent()

    data class ChangeColor(
        val color: Long
    ): EditorEvent()

    data object BackStep: EditorEvent()

    data class ChangeBrushSize(val size: Int): EditorEvent()
    data object ForwardStep: EditorEvent()
    data object ActionStart: EditorEvent()

    data object GetPalette: EditorEvent()
    data class PickColor(val color: Long): EditorEvent()

    data object SaveBitmap: EditorEvent()
}