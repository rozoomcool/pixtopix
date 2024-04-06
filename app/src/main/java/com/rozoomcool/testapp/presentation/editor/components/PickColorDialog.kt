package com.rozoomcool.testapp.presentation.editor.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.skydoves.colorpicker.compose.AlphaSlider
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.ColorPickerController
import com.github.skydoves.colorpicker.compose.HsvColorPicker

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickColorDialog(
    controller: ColorPickerController,
    stateColor: Color,
    onColorPick: (Long) -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(onDismissRequest = { onDismissRequest() }) {
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            AlphaSlider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(35.dp),
                controller = controller,
            )
            BrightnessSlider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(35.dp),
                controller = controller,
            )

            Card(
                Modifier
                    .width(100.dp)
                    .height(40.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(stateColor)
            ) {

            }
            Button(onClick = { onDismissRequest() }) {
                Text("OK")
            }
            HsvColorPicker(
                modifier = Modifier,
                initialColor = stateColor,
                controller = controller,
                onColorChanged = {
                    onColorPick(it.color.value.toLong())
                }
            )
        }
    }
}