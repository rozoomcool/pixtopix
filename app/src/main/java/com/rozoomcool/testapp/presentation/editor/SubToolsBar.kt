package com.rozoomcool.testapp.presentation.editor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.TurnLeft
import androidx.compose.material.icons.rounded.TurnRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rozoomcool.testapp.domain.editorViewModel.EditorEvent
import com.rozoomcool.testapp.domain.editorViewModel.EditorState
import com.rozoomcool.testapp.model.EditorTool
import com.rozoomcool.testapp.presentation.editor.components.EditorPaletteRail

//@Composable
//fun SubToolsBar(
//    editorState: EditorState,
//    onEditorEvent: (EditorEvent) -> Unit
//) {
//
//    when (editorState.editorTool) {
//        is EditorTool.Palette -> {
//            EditorPaletteRail(palette = editorState.palette, onEditorEvent = onEditorEvent)
//        }
//        else -> {}
//    }
//
//}

@Composable
fun SubToolsBar(
    editorState: EditorState,
    onEditorEvent: (EditorEvent) -> Unit,
    onBackStepClickListener: () -> Unit,
    onForwardStepClickListener: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {

        }

        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { onBackStepClickListener() },
            ) {
                Icon(
                    imageVector = Icons.Rounded.TurnLeft,
                    contentDescription = null
                )
            }
            IconButton(
                onClick = { onForwardStepClickListener() },
            ) {
                Icon(
                    imageVector = Icons.Rounded.TurnRight,
                    contentDescription = null
                )
            }
        }
    }
}