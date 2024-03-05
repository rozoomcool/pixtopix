package com.rozoomcool.testapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.FileOpen
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rozoomcool.testapp.ui.animations.StarsAnimation

@Composable
fun StartProjectScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        StarsAnimation()
        Card(
            modifier = Modifier,
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                ElevatedButton(
                    onClick = { /*TODO*/ }) {
                    Row(
                        modifier = Modifier
                    ){
                        Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Новый проект")
                    }
                }
                ElevatedButton(
                    onClick = { /*TODO*/ }) {
                    Row(
                        modifier = Modifier
                    ){
                        Icon(imageVector = Icons.Rounded.FileOpen, contentDescription = null)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Открыть проект")
                    }
                }
            }
        }
    }
}