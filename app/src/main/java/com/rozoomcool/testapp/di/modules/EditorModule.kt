package com.rozoomcool.testapp.di.modules

import com.rozoomcool.testapp.utils.EditorUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class EditorModule {

    @Provides
    @Singleton
    fun provideEditorUtils(): EditorUtils {
        return EditorUtils()
    }
}