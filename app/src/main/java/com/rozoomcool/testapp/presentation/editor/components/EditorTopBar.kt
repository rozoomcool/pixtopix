package com.rozoomcool.testapp.presentation.editor.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material.icons.rounded.SaveAs
import androidx.compose.material.icons.rounded.TurnLeft
import androidx.compose.material.icons.rounded.TurnRight
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditorTopBar(
    title: String = "",
    onBackClickListener: () -> Unit,
    onBackStepClickListener: () -> Unit,
    onForwardStepClickListener: () -> Unit,
    onSaveAsClickListener: () -> Unit,
    onSaveClickListener: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                modifier = Modifier.padding(start = 24.dp),
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight(400)),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { onBackClickListener()},
                ) {
                    Icon(imageVector = Icons.Rounded.ArrowBackIosNew, contentDescription = null)
                }
            }
        },
        actions = {
            IconButton(
                onClick = { onSaveAsClickListener() },
            ) {
                Icon(imageVector = Icons.Rounded.SaveAs, contentDescription = null)
            }
            IconButton(
                onClick = { onSaveClickListener() },
            ) {
                Icon(imageVector = Icons.Rounded.Save, contentDescription = null)
            }
        }
    )
}