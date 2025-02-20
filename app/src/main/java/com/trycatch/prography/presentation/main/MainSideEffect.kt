package com.trycatch.prography.presentation.main

sealed interface MainSideEffect {
    data class NavigateToDetail(
        val id: String,
    ) : MainSideEffect
} 