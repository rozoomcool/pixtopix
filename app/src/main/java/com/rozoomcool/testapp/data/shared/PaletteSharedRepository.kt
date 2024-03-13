package com.rozoomcool.testapp.data.shared

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@SuppressLint("CommitPrefEdits")
class PaletteSharedRepository @Inject constructor(
    @ApplicationContext val context: Context,
    private val editor: SharedPreferences.Editor,
    private val prefs: SharedPreferences = context.getSharedPreferences("palette", Context.MODE_PRIVATE)
) {
    private val palettePrefsKey = "colors"
    init {
        val editor = prefs.edit()
    }

    fun putColor(color: Long) {
        val colors: MutableSet<String> = prefs.getStringSet(palettePrefsKey, setOf())?.toMutableSet() ?: mutableSetOf()
        colors.apply {
            add(color.toString())
        }
        editor.putStringSet(palettePrefsKey, colors)
    }

    fun getColors(): Set<String> {
        return prefs.getStringSet(palettePrefsKey, setOf()) ?: setOf()
    }
}