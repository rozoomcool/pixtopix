package com.rozoomcool.testapp.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Image
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.rozoomcool.testapp.domain.editorViewModel.EditorEvent
import com.rozoomcool.testapp.domain.editorViewModel.EditorState
import com.rozoomcool.testapp.ui.compoents.CustomSingleFormTextField

@Composable
fun CreateProjectDialog(
    editorState: EditorState,
    onEditorEvent: (EditorEvent) -> Unit,
    onDismissRequest: () -> Unit,
    onCreateButtonClickListener: () -> Unit
) {

    val title = remember { mutableStateOf("") }
    val width = remember {
        mutableStateOf("")
    }
    val height = remember {
        mutableStateOf("")
    }


    Dialog(onDismissRequest = { onDismissRequest() }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Column {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    contentColor = colorScheme.onBackground
                ),
                shape = RoundedCornerShape(16.dp),
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Новый проект",
                        style = typography.titleMedium,
                        modifier = Modifier.padding(16.dp),
                    )
                    CustomSingleFormTextField(
                        value = title.value,
                        onValueChanged = { title.value = it },
                        label = "Название"
                    )
                    Spacer(Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.Top
                    ) {
                        Row(
                            modifier = Modifier,
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier
                                    .weight(0.3f)
                                    .padding(start = 8.dp),
                                text = "Высота: "
                            )
                            Box(
                                modifier = Modifier.weight(0.2f)
                            ) {
                                CustomSingleFormTextField(
                                    value = height.value,
                                    onValueChanged = {height.value = it}
                                )
                            }
                            Text(
                                modifier = Modifier
                                    .weight(0.3f)
                                    .padding(start = 8.dp),
                                text = "Ширина: "
                            )
                            Box(
                                modifier = Modifier.weight(0.2f)
                            ) {
                                CustomSingleFormTextField(
                                    value = width.value,
                                    onValueChanged = {width.value = it}
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
            FilledTonalButton(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(
                    topStart = 12.dp,
                    topEnd = 12.dp,
                    bottomStart = 24.dp,
                    bottomEnd = 24.dp
                ),
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = colorScheme.secondaryContainer.copy(alpha = 0.7f)
                ),
                onClick = { onCreateButtonClickListener() }) {
                Text("Создать")
            }
        }
    }
}