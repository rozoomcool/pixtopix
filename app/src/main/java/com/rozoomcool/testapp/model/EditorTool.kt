package com.rozoomcool.testapp.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Colorize
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.FormatColorFill
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.material.icons.twotone.CleaningServices
import androidx.compose.ui.graphics.painter.Painter
import com.rozoomcool.testapp.R

interface SelectableTool
interface DrawableTool
interface ExtendedTool {
    fun getSubTools(): List<SubTool>
}


sealed class EditorTool(
    val imageId: Int
) {
    data object Move: EditorTool(R.drawable.move), SelectableTool
    data class Brush(
        val size: Int = 1
    ): EditorTool(R.drawable.brush), DrawableTool
    data object Select: EditorTool(R.drawable.select), SelectableTool
//    data object Dimmer: EditorTool(Icons.Rounded.DarkMode), DrawableTool
//    data object Fill: EditorTool(Icons.Rounded.FormatColorFill), DrawableTool
    data class Pipette(
        val selectedColor: Long?
    ): EditorTool(R.drawable.pipette), DrawableTool
    data class Eraser(
        val size: Int = 1
    ): EditorTool(R.drawable.eraser), DrawableTool
    object Palette: EditorTool(R.drawable.palette), ExtendedTool {
        override fun getSubTools(): List<SubTool> {
            return listOf<SubTool>()
        }
    }
}

sealed class SubTool {
    data object ColorPicker: SubTool()
    data object SetSize: SubTool()
    data object BackStep
}

val mainTools = listOf(
    EditorTool.Palette,
    EditorTool.Move,
    EditorTool.Brush(),
    EditorTool.Eraser(),
    EditorTool.Pipette(null),
    EditorTool.Select

)