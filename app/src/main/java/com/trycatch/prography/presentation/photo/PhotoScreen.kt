package com.trycatch.prography.presentation.photo

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.trycatch.prography.data.model.PhotoEntity
import com.trycatch.prography.ui.components.PrographyCard
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlin.math.roundToInt

@Serializable
data object PhotoRoute

@Composable
fun PhotoRoute(
    viewModel: PhotoViewModel = hiltViewModel(),
    onNavigateToDetail: (String) -> Unit = {}
) {
    LaunchedEffect(Unit) {
        viewModel.sideEffects.collect { sideEffect ->
            when (sideEffect) {
                is PhotoSideEffect.NavigateToDetail -> {
                    onNavigateToDetail(
                        sideEffect.id,
                    )
                }
            }
        }
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PhotoScreen(
        uiState = uiState,
        saveBookmark = viewModel::saveBookmark,
        onDetailClick = viewModel::navigateToDetail,
    )
}

@Composable
fun PhotoScreen(
    uiState: PhotoUiState,
    saveBookmark: (PhotoEntity) -> Unit = {},
    onDetailClick: (String) -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        uiState.photos.forEach { photo ->
            key(photo.id) {
                SwipeCard(
                    photo = photo,
                    saveBookmark = {
                        saveBookmark(photo)
                    },
                    onDetailClick = {
                        onDetailClick(photo.id)
                    },
                )
            }
        }
    }
}

@Composable
fun SwipeCard(
    photo: PhotoEntity,
    saveBookmark: () -> Unit = {},
    onDetailClick: () -> Unit = {},
    onRemove: () -> Unit = {}
) {
    val offsetX = remember { Animatable(0f) }
    val offsetY = remember { Animatable(0f) }
    val rotation = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 24.dp,
                vertical = 28.dp
            )
            .padding(bottom = 16.dp)
            .offset { 
                IntOffset(
                    x = offsetX.value.roundToInt(),
                    y = offsetY.value.roundToInt()
                )
            }
            .graphicsLayer {
                rotationZ = rotation.value
            }
            .pointerInput(Unit) {
                detectHorizontalDragGestures(
                    onDragEnd = {
                        coroutineScope.launch {
                            val threshold = size.width * 0.4f
                            if (offsetX.value > threshold) {
                                // 오른쪽으로 스와이프 - 북마크에 저장
                                launch { 
                                    offsetX.animateTo(size.width * 2f, tween(400))
                                    saveBookmark()
                                }
                                launch {
                                    offsetY.animateTo(-size.height * 1.5f, tween(400))
                                }
                            } else if (offsetX.value < -threshold) {
                                // 왼쪽으로 스와이프 - 그냥 제거
                                launch { 
                                    offsetX.animateTo(-size.width * 2f, tween(400))
                                    onRemove()
                                }
                                launch {
                                    offsetY.animateTo(-size.height * 1.5f, tween(400))
                                }
                            } else {
                                // 원위치
                                launch {
                                    offsetX.animateTo(0f, spring())
                                }
                                launch {
                                    offsetY.animateTo(0f, spring())
                                }
                                launch {
                                    rotation.animateTo(0f, spring())
                                }
                            }
                        }
                    },
                    onHorizontalDrag = { change, dragAmount ->
                        change.consume()
                        coroutineScope.launch {
                            offsetX.snapTo(offsetX.value + dragAmount)
                            offsetY.snapTo(
                                if (offsetX.value < 0)
                                    offsetY.value + dragAmount
                                else
                                    offsetY.value - dragAmount
                            )
                            rotation.snapTo(offsetX.value * 0.1f)
                        }
                    }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        PrographyCard(
            modifier = Modifier.fillMaxWidth(),
            image = photo.url,
            onBookmarkClick = saveBookmark,
            onDetailClick = onDetailClick
        )
    }
}
