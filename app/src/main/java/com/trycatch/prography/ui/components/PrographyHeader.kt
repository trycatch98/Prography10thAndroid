package com.trycatch.prography.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trycatch.prography.ui.theme.PrographyTheme

@Composable
fun PrographyHeader(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            modifier = Modifier
                .padding(
                    top = 10.dp,
                    bottom = 9.dp,
                    start = 20.dp,
                    end = 20.dp
                ),
            text = text,
            style = PrographyTheme.typography.typo20Bold,
            color = PrographyTheme.colorScheme.black
        )
    }
}

@Preview
@Composable
fun PrographyHeaderPreview() {
    PrographyHeader("북마크")
}