package com.trycatch.prography.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
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
class MainViewModel @Inject constructor(
    private val photoRepository: PhotoRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    private val _sideEffects = MutableSharedFlow<MainSideEffect>()
    val sideEffects = _sideEffects.asSharedFlow()

    val photos = photoRepository.getPhotos()
        .cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            photoRepository.getBookmarks()
                .collect { bookmarks ->
                    _uiState.update {
                        it.copy(bookmarks = bookmarks)
                    }
                }
        }
    }

    fun clickImage(id: String) {
        viewModelScope.launch {
            _sideEffects.emit(MainSideEffect.NavigateToDetail(id))
        }
    }
}