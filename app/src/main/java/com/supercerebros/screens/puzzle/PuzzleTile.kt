package com.supercerebros.screens.puzzle



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp

data class PuzzleTile(val number: Int, val isBlank: Boolean, val imageBitmap: ImageBitmap?)

@Composable
fun Tile(tile: PuzzleTile, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(if (tile.isBlank) Color.Gray else Color.Transparent)
            .padding(4.dp)
    ) {
        if (!tile.isBlank && tile.imageBitmap != null) {
            Image(
                bitmap = tile.imageBitmap,
                contentDescription = "Puzzle tile ${tile.number}"
            )
        }
    }
}
