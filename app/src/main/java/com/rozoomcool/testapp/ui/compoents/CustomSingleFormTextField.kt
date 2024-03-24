package com.rozoomcool.testapp.ui.compoents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomSingleFormTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    label: String = "",
    validator: (String) -> Boolean = fun(_: String) = true,

    ) {
    val isError = remember {
        mutableStateOf(false)
    }
    val textStyle = LocalTextStyle.current.copy(
        color = colorScheme.onSurface
    )

    BasicTextField(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(6.dp))
        .background(colorScheme.background)
        .border(width = 2.dp, if (isError.value) colorScheme.error else Color.Transparent),
        value = value,
        onValueChange = {
            onValueChanged(it)
            isError.value = !validator(it)
        },
        textStyle = textStyle,
        cursorBrush = SolidColor(colorScheme.primary),
        singleLine = true,
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    Modifier
                        .padding(vertical = 6.dp, horizontal = 8.dp)
                ) {
                    if (value == "") Text(
                        text = label,
                        style = textStyle.copy(color = Color.Gray)
                    )
                    innerTextField()
                }
            }
        })
}