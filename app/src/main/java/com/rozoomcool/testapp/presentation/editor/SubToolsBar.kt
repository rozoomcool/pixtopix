package com.rozoomcool.testapp.presentation.editor

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.TurnLeft
import androidx.compose.material.icons.rounded.TurnRight
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
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
            FilledIconButton(
                modifier = Modifier
                    .size(48.dp)
                    .animateContentSize(),
                shape = RoundedCornerShape(12.dp),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.background
                ),
                onClick = {
                    onBackStepClickListener()
                }) {
                Icon(Icons.Rounded.TurnLeft, null)
            }
            Spacer(modifier = Modifier.width(8.dp ))
            FilledIconButton(
                modifier = Modifier
                    .size(48.dp)
                    .animateContentSize(),
                shape = RoundedCornerShape(12.dp),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                    contentColor = MaterialTheme.colorScheme.background
                ),
                onClick = {
                    onForwardStepClickListener()
                }) {
                Icon(Icons.Rounded.TurnRight, null)
            }
        }
    }
}