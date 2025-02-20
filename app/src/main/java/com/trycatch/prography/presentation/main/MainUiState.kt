package com.trycatch.prography.presentation.main

import com.trycatch.prography.data.model.BookmarkEntity
import com.trycatch.prography.data.model.PhotoEntity

data class MainUiState(
    val bookmarks: List<BookmarkEntity> = emptyList()
)