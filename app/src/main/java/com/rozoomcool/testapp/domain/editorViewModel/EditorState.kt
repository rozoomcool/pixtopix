package com.rozoomcool.testapp.domain.editorViewModel

import android.companion.AssociatedDevice
import android.nfc.cardemulation.HostNfcFService
import android.util.Log
import androidx.compose.runtime.Immutable
import com.rozoomcool.testapp.model.EditorTool
import com.rozoomcool.testapp.model.FieldStack
import com.rozoomcool.testapp.model.Pixel
import java.sql.SQLData

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
            if (currentIndex == MAX_STEPS - 1) 1 else 0,
            editorActions.size
        ).apply {
            if (isNotEmpty()) add(editorActions[currentIndex])
            else add(EditorAction(setOf()))
        }

        return copy(
            editorActions = newActionList,
            currentIndex = newActionList.size - 1
        )
    }

    fun actionStepBack(): EditorActionList {
        if (currentIndex <= 0) {
            return copy(currentIndex = 0)
        }
        return copy(currentIndex = currentIndex - 1)
    }

    fun actionStepForward(): EditorActionList {
        if (currentIndex < editorActions.size - 1) {
            return copy(currentIndex = currentIndex + 1)
        }
        return this
    }

    fun addPixels(pixels: Set<Pixel>): EditorActionList {
        val newEditorActions = editorActions.toMutableList()
        newEditorActions[currentIndex] = EditorAction((editorActions[currentIndex].pixels - pixels) + pixels)

        return copy(
            editorActions = newEditorActions
        )
    }

    fun removePixels(pixels: Set<Pixel>): EditorActionList {
        if (editorActions.isEmpty()) {
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
//@immutable
//data class Rosul(
//    val name: String,
//    val surname: String,
//    val poshly: Boolean = true,
//    val fathername: String,
//    val pixels: Set<Pixel>
//
//)
//fun Rosul (
//    boolean: Boolean,
//    fathername: String,
//    sqlData: SQLData,
//    associatedDevice: AssociatedDevice,
//    associatedDevice: AssociatedDevice,
//    hostNfcFService: HostNfcFService
//)
//fun Ros (
//    delete_project()
//    delete_windows_10()
//    delete_github()
//    rosuol_poshly()
//    koklin_delete()
//)