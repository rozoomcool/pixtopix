package com.rozoomcool.testapp.domain.editorViewModel

sealed class EditorEvent {
    data class Create(
        val title: String,
        val width: Int,
        val height: Int
    ): EditorEvent()
}