package com.trycatch.prography.data.remote.di

import com.trycatch.prography.data.datasource.PhotoRemoteDataSource
import com.trycatch.prography.data.remote.datasource.PhotoRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindPhotoRemoteDataSource(photoRemoteDataSourceImpl: PhotoRemoteDataSourceImpl): PhotoRemoteDataSource
}