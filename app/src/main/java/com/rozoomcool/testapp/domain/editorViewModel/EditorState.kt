package com.rozoomcool.testapp.domain.editorViewModel

import com.rozoomcool.testapp.model.EditorTool
import com.rozoomcool.testapp.model.FieldStack
import com.rozoomcool.testapp.model.Pixel

data class EditorState(
    val title: String = "",
    val editorTool: EditorTool = EditorTool.Move,
    val field: PainterField? = null,
    val palette: Set<Long> = setOf(),
    val cColor: Long = 0xFF912750,
    val cBrushSize: Int = 1,
    val fieldStack: FieldStack = FieldStack()
) {

    data class PainterField(
        val width: Int,
        val height: Int,
        val actions: List<Action> = listOf(),
        val layers: List<Layer> = listOf(Layer(name = "default")),
        val type: FieldType
    ) {

        fun pixels(): Set<Pixel> {
            if (actions.isEmpty()) return setOf()
            return actions.last().pixels
        }

        data class Layer(
            val name: String,
            val visible: Boolean = true
        )

        data class Action(
            val pixels: Set<Pixel>
        )
        sealed interface FieldType {
            data object Transparent : FieldType
            data object Filled : FieldType
        }
    }
}