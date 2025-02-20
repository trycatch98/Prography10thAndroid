package com.trycatch.prography.presentation.photo

import com.trycatch.prography.data.model.PhotoEntity

data class PhotoUiState(
    val photos: List<PhotoEntity> = emptyList(),
    val swipePhotos: List<PhotoEntity> = emptyList()
)