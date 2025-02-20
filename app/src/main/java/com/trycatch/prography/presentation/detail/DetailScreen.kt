package com.trycatch.prography.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.trycatch.prography.ui.PrographyIcons
import com.trycatch.prography.ui.components.PrographyFixedWidthImage
import com.trycatch.prography.ui.theme.PrographyTheme
import kotlinx.serialization.Serializable

@Serializable
data class DetailRoute(
    val id: String
)

@Composable
fun DetailRoute(
    viewModel: DetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffects.collect { sideEffect ->
            when (sideEffect) {
                DetailSideEffect.NavigateToBack -> {}
            }
        }
    }

    DetailScreen(
        uiState = uiState
    )
}

@Composable
private fun DetailScreen(
    uiState: DetailUiState,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = PrographyTheme.colorScheme.black.copy(alpha = 0.9f)
            ),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 10.dp,
                    horizontal = 16.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(
                        color = PrographyTheme.colorScheme.white,
                        shape = RoundedCornerShape(24.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = PrographyTheme.colorScheme.gray30,
                        shape = RoundedCornerShape(24.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(PrographyIcons.X),
                    tint = PrographyTheme.colorScheme.black,
                    contentDescription = "close"
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                modifier = Modifier.weight(1f),
                text = uiState.username,
                style = PrographyTheme.typography.typo20Bold,
                color = PrographyTheme.colorScheme.white
            )

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                modifier = Modifier
                    .size(20.dp),
                painter = painterResource(PrographyIcons.Download),
                tint = PrographyTheme.colorScheme.white,
                contentDescription = "download"
            )

            Spacer(modifier = Modifier.width(24.dp))

            Icon(
                modifier = Modifier
                    .size(20.dp),
                painter = painterResource(PrographyIcons.Bookmark),
                tint = PrographyTheme.colorScheme.white,
                contentDescription = "download"
            )
        }

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center
        ) {
            PrographyFixedWidthImage(
                modifier = Modifier
                    .padding(horizontal = 12.dp),
                image = uiState.image,
                imageWidth = uiState.imageWidth,
                imageHeight = uiState.imageHeight,
                maxHeight = 546.dp
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 10.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = uiState.title,
                style = PrographyTheme.typography.typo20Bold,
                color = PrographyTheme.colorScheme.white,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = uiState.description,
                style = PrographyTheme.typography.typo15,
                color = PrographyTheme.colorScheme.white,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = uiState.tags,
                style = PrographyTheme.typography.typo15,
                color = PrographyTheme.colorScheme.white,
                maxLines = 1,
            )
        }
    }
}