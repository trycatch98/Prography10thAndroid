package com.trycatch.prography.presentation.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.trycatch.prography.data.model.PhotoEntity
import com.trycatch.prography.ui.components.PrographyFixedHeightImage
import com.trycatch.prography.ui.components.PrographyFixedWidthImage
import com.trycatch.prography.ui.components.PrographyHeader
import kotlinx.serialization.Serializable

@Serializable
data object MainRoute

@Composable
fun MainRoute(
    viewModel: MainViewModel = hiltViewModel(),
    onNavigateToDetail: (String) -> Unit = {},
) {
    LaunchedEffect(Unit) {
        viewModel.sideEffects.collect { sideEffect ->
            when (sideEffect) {
                is MainSideEffect.NavigateToDetail -> {
                    onNavigateToDetail(
                        sideEffect.id,
                    )
                }
            }
        }
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val photoPagingItems = viewModel.photos.collectAsLazyPagingItems()

    MainScreen(
        uiState = uiState,
        photoPagingItems = photoPagingItems,
        onClickImage = viewModel::clickImage
    )
}

@Composable
fun MainScreen(
    uiState: MainUiState,
    photoPagingItems: LazyPagingItems<PhotoEntity>,
    onClickImage: (String) -> Unit,
) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier.fillMaxSize(),
        columns = StaggeredGridCells.Fixed(2),
        verticalItemSpacing = 10.dp,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 20.dp
        )
    ) {
        // 북마크 섹션
        if (uiState.bookmarks.isNotEmpty()) {
            item(span = StaggeredGridItemSpan.FullLine) {
                PrographyHeader(text = "북마크")
            }

            item(span = StaggeredGridItemSpan.FullLine) {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    items(uiState.bookmarks) {
                        PrographyFixedHeightImage(
                            modifier = Modifier.height(128.dp),
                            image = it.url,
                            imageWidth = it.width,
                            imageHeight = it.height,
                            onClick = {
                                onClickImage(it.id)
                            }
                        )
                    }
                }
            }
        }

        // 최신 이미지 섹션
        item(span = StaggeredGridItemSpan.FullLine) {
            PrographyHeader(text = "최신 이미지")
        }

        items(photoPagingItems.itemCount) { index ->
            photoPagingItems[index]?.let { photo ->
                PrographyFixedWidthImage(
                    image = photo.url,
                    imageWidth = photo.width,
                    imageHeight = photo.height,
                    title = photo.title,
                    onClick = {
                        onClickImage(photo.id)
                    }
                )
            }
        }
    }
}