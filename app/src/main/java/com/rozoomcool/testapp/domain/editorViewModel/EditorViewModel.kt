package com.rozoomcool.testapp.domain.editorViewModel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class EditorViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow<EditorState>(EditorState.Initial)

}