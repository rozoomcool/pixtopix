package com.rozoomcool.testapp.domain.editorViewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class EditorViewModel: ViewModel() {

    private val _state = MutableStateFlow<EditorState>(EditorState.Initial)
    val state: StateFlow<EditorState> = _state.asStateFlow()

    fun onEvent(event: EditorEvent) {

    }

}