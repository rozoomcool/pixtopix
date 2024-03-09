package com.rozoomcool.testapp.domain.editorViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditorViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow<EditorState>(EditorState())
    val state: StateFlow<EditorState> = _state.asStateFlow()

    fun onEvent(event: EditorEvent) {
        when (event) {
            is EditorEvent.Create -> {
                viewModelScope.launch {
                    onCreate(event.title, event.width, event.height)
                }
            }
        }
    }

    private fun onCreate(title: String, width: Int, height: Int) {
            Log.d("@@@", "YES")
            _state.value = _state.value.copy(
                title = title,
                width = width,
                height = height
            )
            Log.d("@@@", "${_state.value}")
    }

}