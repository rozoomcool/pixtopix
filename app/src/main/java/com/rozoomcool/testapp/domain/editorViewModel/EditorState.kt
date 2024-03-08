package com.rozoomcool.testapp.domain.editorViewModel

import com.rozoomcool.testapp.model.EditorTool

sealed class EditorState(
    val editorTool: EditorTool,
    field: PainterField
) {
    data object Initial: EditorState(
        editorTool = EditorTool.Move,
        field = PainterField.Empty
    )
}