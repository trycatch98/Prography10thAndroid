package com.trycatch.prography.presentation.photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trycatch.prography.data.model.BookmarkEntity
import com.trycatch.prography.data.model.PhotoEntity
import com.trycatch.prography.data.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val photoRepository: PhotoRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(PhotoUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffects = MutableSharedFlow<PhotoSideEffect>()
    val sideEffects = _sideEffects.asSharedFlow()

    init {
        viewModelScope.launch {
            photoRepository.getRandomPhotos()
                .collect { photos ->
                    _uiState.update { state ->
                        state.copy(
                            photos = photos,
                            swipePhotos = photos.take(5)
                        )
                    }
                }
        }
    }

    fun saveBookmark(photo: PhotoEntity) {
        viewModelScope.launch {
            photoRepository.toggleBookmark(
                BookmarkEntity(
                    id = photo.id,
                    width = photo.width,
                    height = photo.height,
                    url = photo.url
                )
            )
        }
    }

    fun navigateToDetail(id: String) {
        viewModelScope.launch {
            _sideEffects.emit(PhotoSideEffect.NavigateToDetail(id))
        }
    }
}