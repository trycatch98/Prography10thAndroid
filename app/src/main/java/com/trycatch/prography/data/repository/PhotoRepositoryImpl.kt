package com.trycatch.prography.data.repository

import PhotoPagingSource
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
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

    override fun getPhoto(id: String): Flow<PhotoEntity> = flow {
        emit(photoRemoteDataSource.getPhoto(id))
    }

    override fun getPhotos(): Flow<PagingData<PhotoEntity>> = Pager(
        config = PagingConfig(
            pageSize = 30,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            PhotoPagingSource(photoRemoteDataSource)
        }
    ).flow

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