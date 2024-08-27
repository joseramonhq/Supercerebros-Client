package com.supercerebros.screens.puzzle

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PuzzleGrid(tiles: List<PuzzleTile>, onTileClicked: (Int) -> Unit) {
    val gridSize = 4 // Tamaño del puzzle 4x4
    Column {
        for (row in 0 until gridSize) {
            Row {
                for (col in 0 until gridSize) {
                    val tile = tiles[row * gridSize + col]
                    Tile(
                        tile = tile,
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(1f)
                            .clickable { onTileClicked(row * gridSize + col) }
                    )
                }
            }
        }
    }
}
