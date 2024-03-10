package com.rozoomcool.testapp.presentation.editor

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.rozoomcool.testapp.domain.editorViewModel.EditorEvent
import com.rozoomcool.testapp.domain.editorViewModel.EditorState
import com.rozoomcool.testapp.model.Pixel
import com.rozoomcool.testapp.ui.compoents.EditorBottomBar
import com.rozoomcool.testapp.ui.compoents.EditorTopBar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@SuppressLint("MutableCollectionMutableState", "CoroutineCreationDuringComposition")
@Composable
fun EditorScreen(
    editorState: EditorState,
    onEditorEvent: (EditorEvent) -> Unit
) {

    val rows = editorState.field.height
    val cols = editorState.field.width

    val pixels = editorState.field.pixels

    var scaleFactor by remember {
        mutableFloatStateOf(1f)
    }

    var offsetFactor by remember {
        mutableStateOf(Offset.Zero)
    }

    Scaffold(
        topBar = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                EditorTopBar(editorState.title)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(elevation = 4.dp)
                        .background(colorScheme.background)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("текущий слой")
                }
            }
        },
        bottomBar = {
            EditorBottomBar()
        }
    ) { paddingValues ->

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .zIndex(1f)
                .padding(paddingValues)
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        scaleFactor *= zoom

                        offsetFactor += Offset(pan.x / 2, pan.y / 2)
                    }
                }
        ) {

            val pixelSize: Float = (LocalConfiguration.current.screenWidthDp / cols).toFloat()
            CanvasField(
                cols = cols,
                rows = rows,
                pixels = pixels,
                scaleFactor = scaleFactor,
                offsetFactor = offsetFactor,
                onEditorEvent = onEditorEvent,
                pixelSize = pixelSize,
                zIndex = 0f
            )

        }
    }
}