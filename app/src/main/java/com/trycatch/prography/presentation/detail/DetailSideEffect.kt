package com.trycatch.prography.presentation.detail

sealed interface DetailSideEffect {
    data object NavigateToBack : DetailSideEffect
}