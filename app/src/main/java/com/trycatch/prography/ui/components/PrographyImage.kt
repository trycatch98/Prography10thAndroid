package com.trycatch.prography.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.trycatch.prography.ui.theme.PrographyTheme
import kotlinx.coroutines.flow.Flow

@Composable
fun PrographyFixedWidthImage(
    image: String,
    imageWidth: Int,
    imageHeight: Int,
    modifier: Modifier = Modifier,
    title: String? = null,
    onClick: () -> Unit = {}
) {
    val aspectRatio = imageWidth.toFloat() / imageHeight.toFloat()
    PrographyImage(
        image = image,
        aspectRatio = aspectRatio,
        modifier = modifier,
        title = title,
        onClick = onClick
    )
}

@Composable
fun PrographyFixedHeightImage(
    image: String,
    imageWidth: Int,
    imageHeight: Int,
    modifier: Modifier = Modifier,
    title: String? = null,
    onClick: () -> Unit = {}
) {
    val aspectRatio = imageHeight.toFloat() / imageWidth.toFloat()
    PrographyImage(
        image = image,
        aspectRatio = aspectRatio,
        modifier = modifier,
        title = title,
        onClick = onClick
    )
}

@Composable
private fun PrographyImage(
    image: String,
    aspectRatio: Float,
    modifier: Modifier = Modifier,
    title: String? = null,
    onClick: () -> Unit = {}
) {


    Box(
        modifier = modifier
            .aspectRatio(aspectRatio)
            .clip(shape = RoundedCornerShape(10.dp))
            .clickable { onClick() }
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            model = image,
            loading = {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = PrographyTheme.colorScheme.gray60
                    )
                }
            },
            contentDescription = title,
            contentScale = ContentScale.FillBounds
        )

        if (title != null) {
            Text(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .align(Alignment.BottomStart),
                text = title,
                color = PrographyTheme.colorScheme.white,
                style = PrographyTheme.typography.typo13,
                maxLines = 2,
            )
        }
    }
}

@Preview
@Composable
fun PrographyListItemPreview() {
    Row {
        PrographyFixedWidthImage(
            modifier = Modifier
                .size(172.dp)
                .background(color = Color.Red),
            image = "",
            imageWidth = 1000,
            imageHeight = 800,
            title = "titletitletitle\n타이틀은최대2줄까지\ntestest"
        )

        PrographyFixedHeightImage(
            modifier = Modifier
                .size(172.dp)
                .background(color = Color.Blue),
            image = "",
            imageWidth = 1000,
            imageHeight = 800,
            title = "titletitletitle\n타이틀은최대2줄까지\ntestest"
        )
    }
}