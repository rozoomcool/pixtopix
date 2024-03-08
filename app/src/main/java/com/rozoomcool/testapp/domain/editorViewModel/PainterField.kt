package com.rozoomcool.testapp.domain.editorViewModel

import androidx.compose.ui.geometry.Size
import com.rozoomcool.testapp.model.Pixel

sealed class PainterField(
    val width: Int,
    val height: Int,
    selected: Pair<Int, Int> = Pair(width, height),
    pixels: List<Pixel>
) {
    data object Empty: PainterField(width = 0, height = 0, pixels = listOf())
}