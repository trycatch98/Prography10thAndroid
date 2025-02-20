package com.trycatch.prography.data.repository

import com.trycatch.prography.data.datasource.PhotoLocalDataSource
import com.trycatch.prography.data.datasource.PhotoRemoteDataSource
import com.trycatch.prography.data.model.BookmarkEntity
import com.trycatch.prography.data.model.PhotoEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val photoRemoteDataSource: PhotoRemoteDataSource,
    private val photoPreferencesDataSource: PhotoLocalDataSource
): PhotoRepository {
    override suspend fun getPhotos(
        page: Int,
        perPage: Int
    ): Flow<List<PhotoEntity>> = flow {
        emit(photoRemoteDataSource.getPhotos(page, perPage))
    }

    override suspend fun getRandomPhotos(
        count: Int
    ): Flow<List<PhotoEntity>> = flow {
        emit(photoRemoteDataSource.getRandomPhotos(count))
    }

    override fun getBookmarks(): Flow<List<BookmarkEntity>> =
        photoPreferencesDataSource.getBookmarks()

    override suspend fun toggleBookmark(photo: BookmarkEntity) {
        photoPreferencesDataSource.toggleBookmark(photo)
    }

    override fun isBookmark(photoId: String): Flow<Boolean> =
        photoPreferencesDataSource.isBookmark(photoId)
}