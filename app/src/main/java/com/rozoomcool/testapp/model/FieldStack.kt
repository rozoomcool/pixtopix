package com.rozoomcool.testapp.model

data class FieldStack (
    val currentStep: Int = 0,
    val actions: List<Action> = listOf()
)