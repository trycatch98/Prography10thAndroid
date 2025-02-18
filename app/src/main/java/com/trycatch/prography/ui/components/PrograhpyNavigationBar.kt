package com.trycatch.prography.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trycatch.prography.ui.PrographyIcons
import com.trycatch.prography.ui.theme.LocalColorScheme
import com.trycatch.prography.ui.theme.PrographyTheme

@Composable
fun PrographyNavigationBarItem(
    selected: Boolean,
    onClick: (() -> Unit),
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(52.dp)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            modifier = Modifier
                .size(26.dp),
            painter = painterResource(icon),
            contentDescription = null,
            tint = if (selected)
                LocalColorScheme.current.white
            else
                LocalColorScheme.current.white.copy(alpha = 0.4f)
        )
    }
}

@Composable
fun PrographyNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = LocalColorScheme.current.black,
            ),
        horizontalArrangement = Arrangement
            .spacedBy(
                83.dp,
                Alignment.CenterHorizontally
            ),
        content = content
    )
}

@Preview
@Composable
private fun PrographyBottomNavigationPreview() {
    PrographyTheme {
        PrographyNavigationBar {
            PrographyNavigationBarItem(
                selected = true,
                onClick = { },
                icon = PrographyIcons.House,
            )

            PrographyNavigationBarItem(
                selected = false,
                onClick = { },
                icon = PrographyIcons.Cards,
            )
        }
    }
}