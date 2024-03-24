package com.rozoomcool.testapp.presentation.editor

import androidx.compose.runtime.Composable
import com.rozoomcool.testapp.domain.editorViewModel.EditorEvent
import com.rozoomcool.testapp.domain.editorViewModel.EditorState
import com.rozoomcool.testapp.model.EditorTool
import com.rozoomcool.testapp.presentation.editor.components.EditorPaletteRail

@Composable
fun SubToolsBar(
    editorState: EditorState,
    onEditorEvent: (EditorEvent) -> Unit
) {
    
    when (editorState.editorTool) {
        is EditorTool.Palette -> {
            EditorPaletteRail(palette = editorState.palette, onEditorEvent = onEditorEvent)
        }
        else -> {}
    }
    
}