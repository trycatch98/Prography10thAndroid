package com.trycatch.prography.data.local.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.trycatch.prography.data.datasource.PhotoLocalDataSource
import com.trycatch.prography.data.model.BookmarkEntity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "photos")

@Singleton
class PhotoLocalDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val json: Json
): PhotoLocalDataSource {
    private val bookmarkPhotosKey = stringPreferencesKey("bookmark_photos")

    override fun getBookmarks(): Flow<List<BookmarkEntity>> = context.dataStore.data
        .map { preferences ->
            val photosJson = preferences[bookmarkPhotosKey] ?: "[]"
            json.decodeFromString(photosJson)
        }

    override suspend fun toggleBookmark(photo: BookmarkEntity) {
        context.dataStore.edit { preferences ->
            val existingPhotosJson = preferences[bookmarkPhotosKey] ?: "[]"
            val existingPhotos: List<BookmarkEntity> = json.decodeFromString(existingPhotosJson)

            val updatedPhotos = if (existingPhotos.any { it.id == photo.id }) {
                existingPhotos.filter { it.id != photo.id }
            } else {
                existingPhotos + photo
            }

            preferences[bookmarkPhotosKey] = json.encodeToString(updatedPhotos)
        }
    }

    override fun isBookmark(photoId: String): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            val photosJson = preferences[bookmarkPhotosKey] ?: "[]"
            val photos: List<BookmarkEntity> = json.decodeFromString(photosJson)
            photos.any { it.id == photoId }
        }
    }
}