package com.rozoomcool.testapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.rozoomcool.testapp.ui.compoents.EditorTopBar


@Composable
fun EditorScreen() {
    Scaffold(
        topBar = {
            EditorTopBar()
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 4.dp)
                .background(colorScheme.background)
                .padding(8.dp)
                .align(Alignment.TopCenter),
                contentAlignment = Alignment.Center){
                Text("текущий слой")
            }
        }

    }
}