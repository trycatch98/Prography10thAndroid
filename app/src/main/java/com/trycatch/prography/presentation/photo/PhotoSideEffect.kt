package com.trycatch.prography.presentation.photo

sealed interface PhotoSideEffect {
    data class NavigateToDetail(val id: String) : PhotoSideEffect
}