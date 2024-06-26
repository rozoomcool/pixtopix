package com.rozoomcool.testapp.presentation.editor

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rozoomcool.testapp.domain.editorViewModel.EditorEvent
import com.rozoomcool.testapp.domain.editorViewModel.EditorState
import com.rozoomcool.testapp.model.DrawableTool
import com.rozoomcool.testapp.model.ExtendedTool
import com.rozoomcool.testapp.model.SelectableTool
import com.rozoomcool.testapp.model.mainTools
import com.rozoomcool.testapp.presentation.editor.components.CustomBottomSheet
import com.rozoomcool.testapp.presentation.editor.components.EditorBottomBar
import com.rozoomcool.testapp.presentation.editor.components.EditorPaletteRail
import com.rozoomcool.testapp.presentation.editor.components.EditorTopBar
import com.rozoomcool.testapp.presentation.editor.components.LayersBar
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("MutableCollectionMutableState", "CoroutineCreationDuringComposition")
@Composable
fun EditorScreen(
    editorState: EditorState,
    onEditorEvent: (EditorEvent) -> Unit,
    onBackButtonClick: () -> Unit
) {

    if (editorState.field == null) {
        onBackButtonClick()
    }

    var scaleFactor by remember {
        mutableFloatStateOf(0.9f)
    }

    var offsetFactor by remember {
        mutableStateOf(Offset.Zero)
    }

    var isModalSheetExpanded by remember {
        mutableStateOf(false)
    }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                EditorTopBar(
                    editorState.title,
                    onBackClickListener = { onBackButtonClick() },
                    onSaveAsClickListener = { isModalSheetExpanded = true },
                    onSaveClickListener = {}
                )
//                LayersBar(layers = editorState.field!!.layers)
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier.height(112.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                SubToolsBar(
                    editorState = editorState,
                    onEditorEvent = onEditorEvent,
                    onBackStepClickListener = { onEditorEvent(EditorEvent.BackStep) },
                    onForwardStepClickListener = { onEditorEvent(EditorEvent.ForwardStep) },
                )
                EditorBottomBar(
                    editorState = editorState,
                    mainTools = mainTools,
                    onEditorEvent = onEditorEvent
                )
            }
        }
    ) { paddingValues ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .pointerInput(editorState.editorTool is SelectableTool) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        if (editorState.editorTool is SelectableTool) {
                            scaleFactor *= zoom

                            offsetFactor += Offset(pan.x / 2, pan.y / 2)
                        }
                    }
                }
        ) {
            if (isModalSheetExpanded) {
                CustomBottomSheet(
                    sheetState = sheetState,
                    onDismiss = { isModalSheetExpanded = false },
                    onSave = { onEditorEvent(EditorEvent.SaveBitmap) }
                )
            }

            val rows = editorState.field!!.height
            val cols = editorState.field.width
            val width: Float = LocalConfiguration.current.screenWidthDp.dp.value
            val pixelSize: Float = width / cols.toFloat()
            val height: Float = pixelSize * rows.toFloat()
            val field = editorState.field

            Log.d("===width", "$width")

            CanvasField(
                cols = cols,
                rows = rows,
                pixels = field.pixels(),
                scaleFactor = scaleFactor,
                offsetFactor = offsetFactor,
                onEditorEvent = onEditorEvent,
                width = width,
                height = height,
                pixelSize = (width / cols.toFloat()).dp.toPx(),
                zIndex = 1f,
                isSelectable = editorState.editorTool is SelectableTool,
                isDrawable = editorState.editorTool is DrawableTool
            )

        }
    }
}