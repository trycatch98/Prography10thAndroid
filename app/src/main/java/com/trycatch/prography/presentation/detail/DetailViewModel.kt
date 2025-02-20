package com.trycatch.prography.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.trycatch.prography.data.model.BookmarkEntity
import com.trycatch.prography.data.repository.PhotoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val photoRepository: PhotoRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffects = MutableSharedFlow<DetailSideEffect>()
    val sideEffects = _sideEffects.asSharedFlow()

    private val id: String = savedStateHandle.toRoute<DetailRoute>().id

    init {
        viewModelScope.launch {
            launch {
                photoRepository.getPhoto(id)
                    .collect { photo ->
                        _uiState.update {
                            it.copy(
                                username = photo.username,
                                image = photo.url,
                                imageWidth = photo.width,
                                imageHeight = photo.height,
                                title = photo.title,
                                description = photo.title,
                                tags = photo.tags
                            )
                        }
                    }
            }

            launch {
                photoRepository.isBookmark(id)
                    .collect { isBookmark ->
                        _uiState.update {
                            it.copy(
                                isBookmark = isBookmark
                            )
                        }
                    }
            }
        }
    }

    fun toggleBookmark() {
        viewModelScope.launch {
            val photo = uiState.value
            photoRepository.toggleBookmark(
                BookmarkEntity(
                    id = id,
                    width = photo.imageWidth,
                    height = photo.imageHeight,
                    url = photo.image
                )
            )
        }
    }

    fun navigateBack() {
        viewModelScope.launch {
            _sideEffects.emit(DetailSideEffect.NavigateToBack)
        }
    }
}