package com.trycatch.prography.presentation.detail

data class DetailUiState(
    val username: String = "",
    val isBookmark: Boolean = false,
    val imageWidth: Int = 0,
    val imageHeight: Int = 0,
    val image: String = "",
    val title: String = "",
    val description: String = "",
    val tags: String = ""
)