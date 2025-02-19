package com.trycatch.prography.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.trycatch.prography.ui.PrographyIcons
import com.trycatch.prography.ui.theme.PrographyTheme

@Composable
fun PrographyCard(
    image: String,
    modifier: Modifier = Modifier,
    onBookmarkClick: () -> Unit = {},
    onDetailClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = PrographyTheme.colorScheme.gray30,
                shape = RoundedCornerShape(15.dp)
            )
            .background(
                color = PrographyTheme.colorScheme.white,
                shape = RoundedCornerShape(15.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = modifier
                    .weight(1f)
                    .padding(top = 12.dp)
                    .padding(horizontal = 12.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = PrographyTheme.colorScheme.black)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize(),
                    model = image,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }
            Row(
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .padding(
                        start = 44.dp,
                        end = 43.dp
                    ),
                horizontalArrangement = Arrangement.spacedBy(32.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .border(
                            width = 1.dp,
                            color = PrographyTheme.colorScheme.gray30,
                            shape = RoundedCornerShape(36.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(36.dp),
                        painter = painterResource(PrographyIcons.X),
                        tint = PrographyTheme.colorScheme.gray60,
                        contentDescription = null
                    )
                }

                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .background(
                            color = PrographyTheme.colorScheme.brand,
                            shape = RoundedCornerShape(36.dp)
                        )
                        .clickable(onClick = onBookmarkClick),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(PrographyIcons.Bookmark),
                        tint = PrographyTheme.colorScheme.white,
                        contentDescription = null
                    )
                }

                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .border(
                            width = 1.dp,
                            color = PrographyTheme.colorScheme.gray30,
                            shape = RoundedCornerShape(36.dp)
                        )
                        .clickable(onClick = onDetailClick),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(36.dp),
                        painter = painterResource(PrographyIcons.Information),
                        tint = PrographyTheme.colorScheme.gray60,
                        contentDescription = null
                    )
                }
            }
        }
    }
}


@Preview
@Composable
fun PrographyCardPreview() {
    PrographyCard(
        modifier = Modifier
            .size(
                width = 327.dp,
                height = 553.dp
            ),
        image = ""
    )
}