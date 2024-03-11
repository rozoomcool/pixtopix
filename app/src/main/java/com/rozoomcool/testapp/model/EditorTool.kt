package com.rozoomcool.testapp.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BackHand
import androidx.compose.material.icons.rounded.Brush
import androidx.compose.material.icons.rounded.Colorize
import androidx.compose.material.icons.rounded.CropFree
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.FormatColorFill
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.material.icons.twotone.CleaningServices
import androidx.compose.ui.graphics.vector.ImageVector

interface SelectableTool
interface DrawableTool
interface ExtendedTool {
    fun getSubTools(): List<SubTool>
}


sealed class EditorTool(
    val icon: ImageVector
) {
    data object Move: EditorTool(Icons.Rounded.BackHand), SelectableTool
    data class Brush(
        val size: Int = 1
    ): EditorTool(Icons.Rounded.Brush), DrawableTool
    data object Select: EditorTool(Icons.Rounded.CropFree), SelectableTool
    data object Dimmer: EditorTool(Icons.Rounded.DarkMode), DrawableTool
    data object Fill: EditorTool(Icons.Rounded.FormatColorFill), DrawableTool
    data class Pipette(
        val selectedColor: Long?
    ): EditorTool(Icons.Rounded.Colorize), DrawableTool
    data class Eraser(
        val size: Int = 1
    ): EditorTool(Icons.TwoTone.CleaningServices), DrawableTool
    object Palette: EditorTool(Icons.Rounded.Palette), ExtendedTool {
        override fun getSubTools(): List<SubTool> {
            return listOf<SubTool>()
        }
    }
}

sealed class SubTool {

}

val mainTools = listOf(
    EditorTool.Palette,
    EditorTool.Move,
    EditorTool.Brush(),
    EditorTool.Eraser(),
    EditorTool.Pipette(null),
    EditorTool.Select,
    EditorTool.Dimmer

)