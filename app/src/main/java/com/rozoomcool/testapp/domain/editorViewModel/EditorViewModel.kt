package com.rozoomcool.testapp.domain.editorViewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class EditorViewModel: ViewModel() {

    private val _state = MutableStateFlow<EditorState>(EditorState.Initial)

}