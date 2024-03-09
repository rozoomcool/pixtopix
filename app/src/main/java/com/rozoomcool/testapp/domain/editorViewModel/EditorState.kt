package com.rozoomcool.testapp.domain.editorViewModel

import com.rozoomcool.testapp.model.EditorTool

data class EditorState(
    val title: String = "",
    val width: Int = 1,
    val height: Int = 1,
    val editorTool: EditorTool = EditorTool.Move,
    val field: PainterField = PainterField.Empty
)