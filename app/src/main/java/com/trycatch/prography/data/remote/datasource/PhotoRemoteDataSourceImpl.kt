package com.trycatch.prography.data.remote.datasource

import com.trycatch.prography.data.datasource.PhotoRemoteDataSource
import com.trycatch.prography.data.model.PhotoEntity
import com.trycatch.prography.data.remote.UnsplashApiService
import javax.inject.Inject

class PhotoRemoteDataSourceImpl @Inject constructor(
    private val unsplashApiService: UnsplashApiService
): PhotoRemoteDataSource {
    override suspend fun getPhoto(
        id: String
    ): PhotoEntity =
        unsplashApiService.getPhoto(id)

    override suspend fun getPhotos(
        page: Int,
        perPage: Int
    ): List<PhotoEntity> =
        unsplashApiService.getPhotos(page, perPage)

    override suspend fun getRandomPhotos(
        count: Int
    ): List<PhotoEntity> =
        unsplashApiService.getRandomPhotos(count)
}