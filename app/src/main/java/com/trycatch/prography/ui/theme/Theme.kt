package com.trycatch.prography.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable

private val LightColorScheme = lightColorScheme(
    primary = White,
)

@Composable
fun PrographyTheme(
    colors: PrographyColors = PrographyColors(),
    typography: PrographyTypography = PrographyTypography(),
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme
    CompositionLocalProvider(
        LocalColorScheme provides colors,
        LocalTypography provides typography,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}

object PrographyTheme {
    val colorScheme: PrographyColors
        @Composable @ReadOnlyComposable get() = LocalColorScheme.current

    val typography: PrographyTypography
        @Composable @ReadOnlyComposable get() = LocalTypography.current
}