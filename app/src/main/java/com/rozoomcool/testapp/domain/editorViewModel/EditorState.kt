package com.rozoomcool.testapp.domain.editorViewModel

import android.util.Log
import com.rozoomcool.testapp.model.Action
import com.rozoomcool.testapp.model.EditorTool
import com.rozoomcool.testapp.model.FieldStack
import com.rozoomcool.testapp.model.Pixel

data class EditorState(
    val title: String = "",
    val editorTool: EditorTool = EditorTool.Move,
    val field: PainterField = PainterField(),
    val palette: Set<Long> = setOf(),
    val currentColor: Long = 0xFF333333,
    val fieldStack: FieldStack = FieldStack()
) {
    fun getField(): List<Pixel> {
        return if (fieldStack.actions.isNotEmpty()) fieldStack.actions[fieldStack.currentStep - 1].pixels else listOf()
    }
}