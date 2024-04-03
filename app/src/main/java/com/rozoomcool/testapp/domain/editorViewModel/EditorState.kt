package com.rozoomcool.testapp.domain.editorViewModel

import android.util.Log
import androidx.compose.runtime.Immutable
import com.rozoomcool.testapp.model.EditorTool
import com.rozoomcool.testapp.model.FieldStack
import com.rozoomcool.testapp.model.Pixel

@Immutable
data class EditorState(
    val title: String = "",
    val editorTool: EditorTool = EditorTool.Move,
    val field: PainterField? = null,
    val palette: Set<Long> = setOf(),
    val cColor: Long = 0xFF912750,
    val cBrushSize: Int = 1,
    val fieldStack: FieldStack = FieldStack()
) {

    @Immutable
    data class PainterField(
        val width: Int,
        val height: Int,
        val editorActions: EditorActionList = EditorActionList(),
        val layers: List<Layer> = listOf(Layer(name = "default")),
        val type: FieldType
    ) {

        fun pixels(): Set<Pixel> {
            if (editorActions.isEmpty()) return setOf()
            return editorActions.getCurrentActionPixels()
        }
    }
}

@Immutable
data class EditorActionList(
    private val editorActions: List<EditorAction> = listOf(EditorAction(setOf())),
    val currentIndex: Int = 0
) {

    companion object {
        const val MAX_STEPS = 20
    }

    fun isEmpty(): Boolean = editorActions.isEmpty()
    fun getCurrentActionPixels(): Set<Pixel> = editorActions[currentIndex].pixels

    fun addAction(): EditorActionList {

        val newActionList = editorActions.toMutableList().subList(
                if (currentIndex == MAX_STEPS) 1 else 0,
                editorActions.size
            ).apply {
            add(editorActions[currentIndex])
        }

        return copy(
            editorActions = newActionList,
            currentIndex = minOf(if (currentIndex + 1 < editorActions.size - 1) currentIndex + 1 else editorActions.size - 1, MAX_STEPS)
        )
    }

    fun actionStepBack(): EditorActionList {
        if (currentIndex == 0) {
            return this
        }
        return copy(currentIndex = currentIndex - 1)
    }

    fun actionStepForward(): EditorActionList {
        if (currentIndex < editorActions.size) {
            return copy(currentIndex = currentIndex + 1)
        }
        return this
    }

    fun addPixels(pixels: Set<Pixel>): EditorActionList {
        Log.d("___", "$currentIndex ${editorActions.size}")
        val newEditorActions = editorActions.toMutableList()
        newEditorActions[currentIndex] = EditorAction(editorActions[currentIndex].pixels + pixels)

        return copy(
            editorActions = newEditorActions
        )
    }

    fun removePixels(pixels: Set<Pixel>): EditorActionList {
        if(editorActions.isEmpty()) {
            return this
        }
        val newEditorActions = editorActions.toMutableList()
        newEditorActions[currentIndex] = EditorAction(editorActions[currentIndex].pixels - pixels)

        return copy(
            editorActions = newEditorActions
        )
    }
}

@Immutable
data class EditorAction(
    val pixels: Set<Pixel>
)

sealed interface FieldType {
    @Immutable
    data object Transparent : FieldType

    @Immutable
    data object Filled : FieldType
}

@Immutable
data class Layer(
    val name: String,
    val visible: Boolean = true
)