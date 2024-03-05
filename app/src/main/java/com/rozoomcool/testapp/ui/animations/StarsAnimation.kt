package com.rozoomcool.testapp.ui.animations

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import kotlinx.coroutines.delay

const val STAR_MAX_SIZE = 48

@Composable
fun StarsAnimation() {
    val windowSize = getWindowSize(LocalConfiguration.current, LocalDensity.current.density)

    var stars by remember {
        mutableStateOf(generateStars(windowSize))
    }

    LaunchedEffect(Unit) {
        while (true) {
            stars = updateStars(stars, windowSize)
            delay(1000/60)
        }
    }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
    ) {
        repeat(stars.size) {
            drawCircle(
                Color(0xFFD7C7EB).copy(alpha = 1 - ((0.95f/STAR_MAX_SIZE.toFloat())*stars[it].z)),
                radius = stars[it].z,
                center = Offset(stars[it].x, stars[it].y)
            )
        }
    }

}

data class Star(
    val x: Float,
    val y: Float,
    val z: Float
)

fun updateStars(stars: List<Star>, windowSize: Size): List<Star> {
    val result = mutableListOf<Star>()

    stars.forEach {
        if (it.z.toInt() == STAR_MAX_SIZE) {
            result.add(generateStar(windowSize, zMaxVal = 1))
        } else {
            result.add(Star(it.x, it.y, it.z + 0.1f))
        }
    }

    return result
}

fun generateStars(windowSize: Size, count: Int = 100): List<Star> {
    val stars = mutableListOf<Star>()

    repeat(count) {
        stars.add(generateStar(windowSize))
    }

    return stars
}

fun generateStar(windowSize: Size, zMaxVal: Int = STAR_MAX_SIZE): Star {
    val x = (0 until windowSize.width.toInt()).random().toFloat()
    val y = (0 until windowSize.height.toInt()).random().toFloat()
    val z = (1..zMaxVal).random().toFloat()

    return Star(x, y, z)
}

fun getWindowSize(configuration: Configuration, density: Float): Size {
    val width: Float = (density * configuration.screenWidthDp.dp).value
    val height: Float = (density * configuration.screenHeightDp.dp).value

    return Size(width, height)
}
