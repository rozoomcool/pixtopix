package com.rozoomcool.testapp.domain.editorViewModel

import com.rozoomcool.testapp.model.EditorTool

data class EditorState(
    val title: String = "",
    val editorTool: EditorTool = EditorTool.Move,
    val field: PainterField = PainterField(),
    val currentColor: Long = 0xFF333333
)