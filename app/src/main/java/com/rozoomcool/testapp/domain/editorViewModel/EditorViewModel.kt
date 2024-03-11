package com.rozoomcool.testapp.domain.editorViewModel

import android.util.Log
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rozoomcool.testapp.model.EditorTool
import com.rozoomcool.testapp.model.Pixel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditorViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow<EditorState>(EditorState())
    val state: StateFlow<EditorState> = _state.asStateFlow()

    fun onEvent(event: EditorEvent) {
        when (event) {
            is EditorEvent.Create -> {
                viewModelScope.launch {
                    onCreate(event.title, event.width, event.height)
                }
            }

            is EditorEvent.TapPixel -> {
                viewModelScope.launch {
                    onTapPixel(event.x, event.y, event.size)
                }
            }

            is EditorEvent.PanLine -> {
                viewModelScope.launch {
                    onPanLine(event.start, event.end, event.size)
                }
            }

            is EditorEvent.ChangeTool -> {
                viewModelScope.launch {
                    onChangeTool(event.editorTool)
                }
            }
        }
    }

    private fun onChangeTool(editorTool: EditorTool) {
        _state.value = _state.value.copy(
            editorTool = editorTool
        )
    }

    private fun onCreate(title: String, width: Int, height: Int) {
        Log.d("@@@", "YES")
        _state.value = _state.value.copy(
            title = title,
            field = _state.value.field.copy(
                width = width,
                height = height
            )
        )
        Log.d("@@@", "${_state.value}")
    }

    private fun onTapPixel(x: Float, y: Float, size: IntSize) {
        val localPixelSize = size.width / _state.value.field.width

        val cordX =
            (x / localPixelSize).toInt()
        val cordY =
            (y / localPixelSize).toInt()

        if (cordX !in 0..<_state.value.field.width && cordY !in 0..<_state.value.field.height) {
            return
        }

        val newSet: MutableSet<Pixel> = _state.value.field.pixels.toMutableSet()

        when (_state.value.editorTool) {
            is EditorTool.Brush -> newSet.apply {
                add(
                    Pixel(
                        cordX,
                        cordY,
                        _state.value.currentColor
                    )
                )
            }

            is EditorTool.Eraser -> newSet.apply { remove(Pixel(cordX, cordY, 0)) }
            else -> {}
        }

        _state.value = _state.value.copy(
            field = _state.value.field.copy(
                pixels = newSet.toSet()
            )
        )
    }

    private fun onPanLine(start: Offset, end: Offset, size: IntSize) {
        val localPixelSize = size.width / _state.value.field.width

        val x0 = ((start.x - (start.x % localPixelSize)) / localPixelSize).toInt()
        val x1 = ((end.x - (end.x % localPixelSize)) / localPixelSize).toInt()
        val y0 = ((start.y - (start.y % localPixelSize)) / localPixelSize).toInt()
        val y1 = ((end.y - (end.y % localPixelSize)) / localPixelSize).toInt()

        val points = getPointsOnLine(x0, y0, x1, y1)
        val newSet: MutableSet<Pixel> = _state.value.field.pixels.toMutableSet()

        when (_state.value.editorTool) {
            is EditorTool.Brush -> newSet.apply {
                points.forEach {
                    add(Pixel(it.first, it.second, _state.value.currentColor))
                }
            }

            is EditorTool.Eraser -> newSet.apply {
                points.forEach {
                    remove(Pixel(it.first, it.second, 0))
                }
            }

            else -> {}
        }

        _state.value = _state.value.copy(
            field = _state.value.field.copy(
                pixels = newSet.toSet()
            )
        )
    }

    private fun getPointsOnLine(x0: Int, y0: Int, x1: Int, y1: Int): Set<Pair<Int, Int>> {
        val points = mutableSetOf<Pair<Int, Int>>()

        var x = x0
        var y = y0
        val dx = kotlin.math.abs(x1 - x0)
        val dy = kotlin.math.abs(y1 - y0)
        val sx = if (x0 < x1) 1 else -1
        val sy = if (y0 < y1) 1 else -1
        var err = dx - dy

        while (true) {
            if (x in 0..<_state.value.field.width && y in 0..<_state.value.field.height) {
                points.add(Pair(x, y))
            }
            if (x == x1 && y == y1) {
                break
            }
            val e2 = 2 * err
            if (e2 > -dy) {
                err -= dy
                x += sx
            }
            if (e2 < dx) {
                err += dx
                y += sy
            }
        }

        return points
    }

}