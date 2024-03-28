package com.rozoomcool.testapp.domain.editorViewModel

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rozoomcool.testapp.data.shared.PaletteSharedRepository
import com.rozoomcool.testapp.model.Action
import com.rozoomcool.testapp.model.EditorTool
import com.rozoomcool.testapp.model.Pixel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditorViewModel @Inject constructor(
    private val paletteSharedRepository: PaletteSharedRepository
) : ViewModel() {

    private val _state = MutableStateFlow(EditorState())
    val state: StateFlow<EditorState> = _state.asStateFlow()

    init {
        onEvent(EditorEvent.GetPalette)
    }

    fun onEvent(event: EditorEvent) {
        when (event) {
            is EditorEvent.Create -> {
                viewModelScope.launch {
                    onCreate(event.title, event.width, event.height)
                }
            }

            is EditorEvent.TapPixel -> {
                viewModelScope.launch {
                    onTapPixel(event.x, event.y, event.width)
                }
            }

            is EditorEvent.PanLine -> {
                viewModelScope.launch {
                    onPanLine(event.start, event.end, event.width)
                }
            }

            is EditorEvent.ChangeTool -> {
                viewModelScope.launch {
                    onChangeTool(event.editorTool)
                }
            }

            is EditorEvent.ChangeColor -> {
                viewModelScope.launch {
                    onChangeColor(event.color)
                }
            }

            is EditorEvent.GetPalette -> {
                viewModelScope.launch {
                    onGetPalette()
                }
            }

            is EditorEvent.PickColor -> {
                viewModelScope.launch {
                    onPickColor(event.color)
                }
            }

            is EditorEvent.BackStep -> {
                viewModelScope.launch {
                    onBackStep()
                }
            }

            is EditorEvent.ForwardStep -> {
                viewModelScope.launch {
                }
            }

            is EditorEvent.ActionStart -> {
                viewModelScope.launch {
                    onActionStart()
                }
            }
        }
    }

    private fun onActionStart() {
        _state.value = _state.value.copy(
            field = _state.value.field!!.copy(
                actions = _state.value.field!!.actions.toMutableList().apply {
                    add(EditorState.PainterField.Action(setOf()))
                }
            )
        )
    }

    private fun onBackStep() {
//        var currentStep = _state.value.fieldStack.currentStep
//        if (currentStep > 0) currentStep -= 1
//
//        val actions: MutableList<Action> = _state.value.fieldStack.actions.toMutableList()
//        if (actions.size - currentStep > 5) {
//            actions.apply {
//                this.removeLast()
//            }
//        }
//
//        _state.value = _state.value.copy(
//            fieldStack = _state.value.fieldStack.copy(
//                currentStep = currentStep,
//                actions = actions
//            )
//        )
        if (_state.value.field!!.actions.isNotEmpty()) {
            _state.value = _state.value.copy(
                field = _state.value.field!!.copy(
                    actions = _state.value.field!!.actions.toMutableList().apply {
//                        if(last().pixels.isEmpty()) {
//                            removeLast()
//                        }
                        removeLast()
                    }
                )
            )
        }
    }

    private fun onPickColor(color: Long) {
        paletteSharedRepository.putColor(color)
        _state.value = _state.value.copy(
            currentColor = color
        )
    }

    private fun onGetPalette() {
        val colors = paletteSharedRepository.getColors()
        _state.value = _state.value.copy(
            palette = colors
        )
    }

    private fun onChangeColor(color: Long) {
        _state.value = _state.value.copy(
            currentColor = color
        )
    }

    private fun onChangeTool(editorTool: EditorTool) {
        _state.value = _state.value.copy(
            editorTool = editorTool
        )
    }

    private fun onCreate(title: String, width: Int, height: Int) {
        _state.value = _state.value.copy(
            title = title,
            field = EditorState.PainterField(
                width = width,
                height = height,
                type = EditorState.PainterField.FieldType.Transparent
            )
        )
    }

    private fun onTapPixel(x: Float, y: Float, width: Float) {
        val cols = _state.value.field!!.width
        val rows = _state.value.field!!.height
        val localPixelSize = width / cols

        val cordX =
            (x / localPixelSize).toInt()
        val cordY =
            (y / localPixelSize).toInt()

        if (cordX !in 0..<cols && cordY !in 0..<rows) {
            return
        }

        val pixel = Pixel(cordX, cordY, _state.value.currentColor)
        val newPixels: MutableSet<Pixel> =
            mutableSetOf<Pixel>().apply { addAll(_state.value.field!!.pixels()) }

        when (_state.value.editorTool) {
            is EditorTool.Brush -> newPixels.apply {
                remove(pixel)
                add(pixel)
            }

            is EditorTool.Eraser -> newPixels.apply { remove(pixel); }

            else -> mutableSetOf()
        }

        addPixels(newPixels)
    }

    private fun onPanLine(start: Offset, end: Offset, width: Float) {
        val localPixelSize = width / _state.value.field!!.width

        val x0 = ((start.x - (start.x % localPixelSize)) / localPixelSize).toInt()
        val x1 = ((end.x - (end.x % localPixelSize)) / localPixelSize).toInt()
        val y0 = ((start.y - (start.y % localPixelSize)) / localPixelSize).toInt()
        val y1 = ((end.y - (end.y % localPixelSize)) / localPixelSize).toInt()

        val points = getPointsOnLine(x0, y0, x1, y1)
        val newPixels: MutableSet<Pixel> =
            mutableSetOf<Pixel>().apply { addAll(_state.value.field!!.pixels()) }

        when (_state.value.editorTool) {
            is EditorTool.Brush -> newPixels.apply {
                addAll(points)
                addPixels(newPixels)
            }

            is EditorTool.Eraser -> removePixels(points)

            else -> {}
        }


    }

    private fun removePixels(points: Set<Pixel>) {
        val actions = _state.value.field!!.actions.toMutableList()
        for (i in actions.indices){
            actions[i] = EditorState.PainterField.Action(actions[i].pixels - points)
        }
        _state.value = _state.value.copy(
            field = _state.value.field!!.copy(
                actions = actions
            )
        )
    }

    private fun addPixels(newPixels: MutableSet<Pixel>) {
        val actions: MutableList<EditorState.PainterField.Action> =
            _state.value.field!!.actions.toMutableList()
        val newActionsValue = if (actions.isEmpty()) {
            listOf(EditorState.PainterField.Action(newPixels.toMutableSet()))
        } else {
            newPixels.apply { addAll(actions.last().pixels) }
            actions[actions.size - 1] = EditorState.PainterField.Action(newPixels)
            actions
        }

        _state.value = _state.value.copy(
            field = _state.value.field!!.copy(
                actions = newActionsValue
            )
        )
    }

    private fun getPointsOnLine(x0: Int, y0: Int, x1: Int, y1: Int): Set<Pixel> {
        val points = mutableSetOf<Pixel>()

        var x = x0
        var y = y0
        val dx = kotlin.math.abs(x1 - x0)
        val dy = kotlin.math.abs(y1 - y0)
        val sx = if (x0 < x1) 1 else -1
        val sy = if (y0 < y1) 1 else -1
        var err = dx - dy

        while (true) {
            if (x in 0..<_state.value.field!!.width && y in 0..<_state.value.field!!.height) {
                points.add(Pixel(x, y, _state.value.currentColor))
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