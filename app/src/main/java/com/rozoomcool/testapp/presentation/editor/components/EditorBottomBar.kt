package com.rozoomcool.testapp.presentation.editor.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        FilledIconButton(
            modifier = Modifier
                .size(48.dp)
                .animateContentSize()
                .shadow(2.dp, shape = RoundedCornerShape(12.dp)),
            shape = RoundedCornerShape(12.dp),
            colors = IconButtonDefaults.filledIconButtonColors(
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.background
            ),
            onClick = {}) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(2.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(editorState.cColor))
            ) {

            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        mainTools.mapIndexed { ii, tool ->
            val scale by animateFloatAsState(
                targetValue = if (tool::class == editorState.editorTool::class) 1.13f else 1f,
                label = ""
            )
            FilledIconButton(
                modifier = Modifier
                    .size(48.dp)
                    .scale(scale)
                    .animateContentSize(),
                shape = RoundedCornerShape(12.dp),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = colorScheme.secondary,
                    contentColor = if (tool::class == editorState.editorTool::class) LocalContentColor.current else colorScheme.background
                ),
                onClick = {
                    onEditorEvent(EditorEvent.ChangeTool(tool))
                }) {
                Image(painter = painterResource(id = tool.imageId), contentDescription = null)
            }
            Spacer(modifier = Modifier.width(8.dp))
        }

    }
}