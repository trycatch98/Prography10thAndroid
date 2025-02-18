package com.trycatch.prography.ui.presentation.photo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.serialization.Serializable

@Serializable
data object PhotoRoute

@Composable
fun PhotoRoute() {
    PhotoScreen()
}

@Composable
fun PhotoScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    )
}
