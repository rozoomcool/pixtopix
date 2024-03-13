package com.rozoomcool.testapp.data.shared

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


val standardPalette = mutableSetOf(
    0xFF546E7A, 0xFF757575, 0xFF6D4C41, 0xFFF4511E, 0xFFFB8C00, 0xFFFFB300, 0xFFFDD835,
    0xFF7CB342, 0xFF43A047, 0xFF00897B, 0xFF00ACC1, 0xFF039BE5, 0xFF1E88E5, 0xFF3949AB,
    0xFF3949AB, 0xFF5E35B1, 0xFF8E24AA, 0xFFD81B60, 0xFFE53935
)


@SuppressLint("CommitPrefEdits")
class PaletteSharedRepository(
    val context: Context,
    private val prefs: SharedPreferences = context.getSharedPreferences(
        "palette",
        Context.MODE_PRIVATE
    )
) {
    private val palettePrefsKey = "colors"
    val editor = prefs.edit()

    fun putColor(color: Long) {
        val colors: MutableSet<String> =
            prefs.getStringSet(palettePrefsKey, setOf())?.toMutableSet() ?: mutableSetOf()
        colors.apply {
            add(color.toString())
        }
        editor.putStringSet(palettePrefsKey, colors)
    }

    fun getColors(): Set<Long> {
        val colors = prefs.getStringSet(palettePrefsKey, setOf()) ?: setOf()
        return standardPalette + colors.map {
            it.toLong()
        }.toSet()
    }
}