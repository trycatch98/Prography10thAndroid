package com.trycatch.prography.data.di

import com.trycatch.prography.data.repository.PhotoRepository
import com.trycatch.prography.data.repository.PhotoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindImageRepository(imageRepositoryImpl: PhotoRepositoryImpl): PhotoRepository
}