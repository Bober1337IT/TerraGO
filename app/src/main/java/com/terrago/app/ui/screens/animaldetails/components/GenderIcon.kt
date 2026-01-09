package com.terrago.app.ui.screens.animaldetails.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun GenderIcon(
    gender: String?,
    fontSize: TextUnit = 24.sp,
    modifier: Modifier = Modifier
) {
    if (gender.isNullOrBlank()) return

    val (icon, color) = when (gender) {
        "Female" -> "♀" to Color(0xFFFF69B4)
        "Male" -> "♂" to Color(0xFF2196F3)
        else -> return
    }

    Text(
        text = icon,
        fontSize = fontSize,
        color = color,
        modifier = modifier
    )
}
