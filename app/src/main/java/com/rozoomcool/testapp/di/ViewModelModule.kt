package com.rozoomcool.testapp.di

import com.rozoomcool.testapp.domain.editorViewModel.EditorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { EditorViewModel() }
}