package com.trycatch.prography.data.datasource

import com.trycatch.prography.data.model.PhotoEntity

interface PhotoRemoteDataSource {
    suspend fun getPhoto(
        id: String
    ): PhotoEntity

    suspend fun getPhotos(
        page: Int,
        perPage: Int
    ): List<PhotoEntity>

    suspend fun getRandomPhotos(
        count: Int
    ): List<PhotoEntity>
}