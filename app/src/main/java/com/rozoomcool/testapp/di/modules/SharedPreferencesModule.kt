package com.rozoomcool.testapp.di.modules

import android.content.Context
import com.rozoomcool.testapp.data.shared.PaletteSharedRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {
    @Provides
    @Singleton
    fun providesPaletteSharedRepository(@ApplicationContext context: Context): PaletteSharedRepository {
        return PaletteSharedRepository(context)
    }
}