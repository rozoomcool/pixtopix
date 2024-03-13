package com.rozoomcool.testapp.presentation.editor

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.rozoomcool.testapp.domain.editorViewModel.EditorEvent
import com.rozoomcool.testapp.domain.editorViewModel.EditorState
import com.rozoomcool.testapp.model.EditorTool

@Composable
fun EditorBottomBar(
    editorState: EditorState,
    mainTools: List<EditorTool>,
    onEditorEvent: (EditorEvent) -> Unit
) {
    Row (
        modifier = Modifier
            .padding(12.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
            mainTools.mapIndexed() { ii, tool ->
                FilledIconButton(
                    modifier = Modifier.scale(if (tool::class == editorState.editorTool::class) 1.13f else 1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = colorScheme.surfaceVariant,
                        contentColor = if (tool::class == editorState.editorTool::class) LocalContentColor.current else colorScheme.background
                    ),
                    onClick = {
                        onEditorEvent(EditorEvent.ChangeTool(tool))
                    }) {
                    Icon(
                        imageVector = tool.icon,
                        contentDescription = null
                    )
                }
            }

    }
}