package com.trycatch.prography.data.local.di

import com.trycatch.prography.data.datasource.PhotoLocalDataSource
import com.trycatch.prography.data.local.datasource.PhotoLocalDataSourceImpl
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
    abstract fun bindPhotoLocalDataSource(photoLocalDataSourceImpl: PhotoLocalDataSourceImpl): PhotoLocalDataSource
}