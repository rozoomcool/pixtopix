package com.rozoomcool.testapp.domain.editorViewModel

import androidx.compose.ui.geometry.Size
import com.rozoomcool.testapp.model.Pixel

data class PainterField(
    val width: Int = 1,
    val height: Int = 1,
    val selected: Pair<Int, Int> = Pair(width, height),
    val pixels: Set<Pixel> = setOf()
)