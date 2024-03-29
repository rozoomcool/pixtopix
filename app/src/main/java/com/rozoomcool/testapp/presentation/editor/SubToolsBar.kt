package com.rozoomcool.testapp.presentation.editor

import androidx.compose.animation.animateContentSize
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.TurnLeft
import androidx.compose.material.icons.rounded.TurnRight
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rozoomcool.testapp.domain.editorViewModel.EditorEvent
import com.rozoomcool.testapp.domain.editorViewModel.EditorState
import com.rozoomcool.testapp.model.EditorTool
import com.rozoomcool.testapp.model.ExtendedTool
import com.rozoomcool.testapp.model.SubTool

@Composable
fun SubToolsBar(
    editorState: EditorState,
    onEditorEvent: (EditorEvent) -> Unit,
    onBackStepClickListener: () -> Unit,
    onForwardStepClickListener: () -> Unit
) {
    val currentTool: EditorTool = editorState.editorTool

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (currentTool is ExtendedTool) {
                for (subTool in currentTool.getSubTools()) {
                    when (subTool) {
                        is SubTool.ColorPicker -> {
                            FilledIconButton(
                                modifier = Modifier
                                    .size(48.dp)
                                    .animateContentSize(),
                                shape = RoundedCornerShape(12.dp),
                                colors = IconButtonDefaults.filledIconButtonColors(
                                    containerColor = MaterialTheme.colorScheme.secondary,
                                    contentColor = MaterialTheme.colorScheme.background
                                ),
                                onClick = {
                                    onBackStepClickListener()
                                }) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(6.dp)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Color(editorState.cColor))
                                ) {

                                }
                            }
                        }

                        is SubTool.SetSize -> {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Slider(
                                    modifier = Modifier
                                        .padding(horizontal = 8.dp)
                                        .width(100.dp),
                                    value = editorState.cBrushSize.toFloat(),
                                    onValueChange = {
                                        onEditorEvent(
                                            EditorEvent.ChangeBrushSize(it.toInt())
                                        )
                                    },
                                    colors = SliderDefaults.colors(
                                        thumbColor = MaterialTheme.colorScheme.secondary,
                                        activeTrackColor = MaterialTheme.colorScheme.secondary,
                                        inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                                    ),
                                    steps = 20,
                                    valueRange = 1f..20f
                                )
                                Text(editorState.cBrushSize.toString())
                            }
                        }
                    }
                }
            }

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
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.background
                ),
                onClick = {
                    onBackStepClickListener()
                }) {
                Icon(Icons.Rounded.TurnLeft, null)
            }
            Spacer(modifier = Modifier.width(8.dp))
            FilledIconButton(
                modifier = Modifier
                    .size(48.dp)
                    .animateContentSize(),
                shape = RoundedCornerShape(12.dp),
                colors = IconButtonDefaults.filledIconButtonColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
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