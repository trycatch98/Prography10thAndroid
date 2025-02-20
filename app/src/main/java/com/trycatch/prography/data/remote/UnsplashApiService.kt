package com.trycatch.prography.data.remote

import com.trycatch.prography.data.model.PhotoEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashApiService {
    @GET("/photos/{id}")
    suspend fun getPhoto(
        @Path("id") id: String,
    ): PhotoEntity

    @GET("/photos")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<PhotoEntity>

    @GET("/photos/random")
    suspend fun getRandomPhotos(
        @Query("count") count: Int
    ): List<PhotoEntity>
}