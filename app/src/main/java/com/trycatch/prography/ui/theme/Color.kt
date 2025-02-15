package com.trycatch.prography.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Brand = Color(0xFFD81D45)
val Black = Color(0xFF070707)
val Black90 = Color(0xFF222222)
val Gray20 = Color(0xFFF5F6FA)
val Gray30 = Color(0xFFEAEBEF)
val Gray60 = Color(0xFFB3B3BE)
val White = Color(0xFFFFFFFF)

@Immutable
data class PrographyColors(
    val brand: Color = Brand,
    val black: Color = Black,
    val black90: Color = Black90,
    val gray20: Color = Gray20,
    val gray30: Color = Gray30,
    val gray60: Color = Gray60,
    val white: Color = White
)

internal val LocalColorScheme = staticCompositionLocalOf { PrographyColors() }