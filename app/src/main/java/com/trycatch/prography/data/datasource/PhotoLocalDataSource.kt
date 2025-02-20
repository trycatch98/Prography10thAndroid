package com.trycatch.prography.data.datasource

import com.trycatch.prography.data.model.BookmarkEntity
import kotlinx.coroutines.flow.Flow

interface PhotoLocalDataSource {
    fun getBookmarks(): Flow<List<BookmarkEntity>>
    suspend fun toggleBookmark(photo: BookmarkEntity)
    fun isBookmark(photoId: String): Flow<Boolean>
}