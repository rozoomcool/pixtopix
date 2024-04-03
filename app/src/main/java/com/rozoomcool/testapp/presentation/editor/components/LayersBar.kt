package com.rozoomcool.testapp.presentation.editor.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.RestoreFromTrash
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.rozoomcool.testapp.domain.editorViewModel.EditorState

@Composable
fun LayersBar(layers: List<EditorState.PainterField.Layer>) {

    var isExpanded by remember {
        mutableStateOf(false)
    }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 12.dp)
            .animateContentSize(),
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        if (isExpanded) {
            Column(
                modifier = Modifier
                    .height(if (isExpanded) 100.dp else 0.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 24.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.secondary)
                            .padding(vertical = 4.dp, horizontal = 12.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                IconButton(
                                    modifier = Modifier.size(32.dp),
                                    onClick = { /*TODO*/ }
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Visibility,
                                        contentDescription = null
                                    )
                                }
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(layers.last().name)

                            }
                            IconButton(
                                modifier = Modifier.size(32.dp),
                                onClick = { /*TODO*/ }
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.RestoreFromTrash,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.secondary)
                    .padding(vertical = 4.dp, horizontal = 12.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        IconButton(
                            modifier = Modifier.size(32.dp),
                            onClick = { /*TODO*/ }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Visibility,
                                contentDescription = null
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(layers.last().name)

                    }
                    IconButton(
                        modifier = Modifier.size(32.dp),
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.RestoreFromTrash,
                            contentDescription = null
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(2.dp))

        Icon(
            modifier = Modifier
                .size(32.dp)
                .pointerInput(true) { detectTapGestures { isExpanded = !isExpanded } },
            imageVector = if (isExpanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
            contentDescription = null
        )
    }
}