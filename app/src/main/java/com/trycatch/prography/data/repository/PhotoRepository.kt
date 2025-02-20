package com.trycatch.prography.data.repository

import com.trycatch.prography.data.model.BookmarkEntity
import com.trycatch.prography.data.model.PhotoEntity
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    suspend fun getPhotos(
        page: Int,
        perPage: Int = 30
    ): Flow<List<PhotoEntity>>

    suspend fun getRandomPhotos(
        count: Int = 30
    ): Flow<List<PhotoEntity>>

    fun getBookmarks(): Flow<List<BookmarkEntity>>
    suspend fun toggleBookmark(photo: BookmarkEntity)
    fun isBookmark(photoId: String): Flow<Boolean>
}