package com.rozoomcool.testapp.ui.compoents

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BorderColor
import androidx.compose.material.icons.rounded.Brush
import androidx.compose.material.icons.rounded.ColorLens
import androidx.compose.material.icons.rounded.Colorize
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp

val tools = listOf(
    Icons.Rounded.ColorLens,
    Icons.Rounded.BorderColor,
    Icons.Rounded.Brush,
    Icons.Rounded.Colorize,
)

@Composable
fun EditorBottomBar() {
    Row (
        modifier = Modifier
            .padding(12.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(10){i ->
            tools.mapIndexed() { ii, ic ->
                FilledIconButton(
                    modifier = Modifier.scale(if (i + ii == 0) 1.13f else 1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = colorScheme.surfaceVariant,
                        contentColor = if (i + ii == 0) LocalContentColor.current else colorScheme.background
                    ),
                    onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = ic,
                        contentDescription = null
                    )
                }
            }
        }

    }
}