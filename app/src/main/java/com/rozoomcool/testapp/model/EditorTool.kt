package com.rozoomcool.testapp.model

interface Selectable
interface Drawable
interface ExtendedTool {
    fun getSubTools(): List<SubTool>
}


sealed class EditorTool() {
    data object Move: EditorTool(), Selectable
    data class Brush(
        val size: Int = 1
    ): EditorTool(), Drawable
    data object Select: EditorTool(), Selectable
    data object Dimmer: EditorTool(), Drawable
    data object Fill: EditorTool(), Drawable
    data class Pipette(
        val selectedColor: Long?
    ): EditorTool(), Drawable
    data object Eraser: EditorTool(), Drawable
    object Palette: EditorTool(), ExtendedTool {
        override fun getSubTools(): List<SubTool> {
            return listOf<SubTool>()
        }
    }
}

sealed class SubTool {

}