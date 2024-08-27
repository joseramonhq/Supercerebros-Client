package com.supercerebros.screens.puzzle


import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

fun divideImage(resourceId: Int, context: Context, gridSize: Int): List<ImageBitmap> {
    val originalBitmap = BitmapFactory.decodeResource(context.resources, resourceId)
    val pieceWidth = originalBitmap.width / gridSize
    val pieceHeight = originalBitmap.height / gridSize

    val pieces = mutableListOf<ImageBitmap>()

    for (row in 0 until gridSize) {
        for (col in 0 until gridSize) {
            val pieceBitmap = Bitmap.createBitmap(originalBitmap, col * pieceWidth, row * pieceHeight, pieceWidth, pieceHeight)
            pieces.add(pieceBitmap.asImageBitmap())
        }
    }

    return pieces
}

fun generateInitialPuzzleState(context: Context, resourceId: Int, gridSize: Int = 4): PuzzleState {
    val pieces = divideImage(resourceId, context, gridSize)
    val shuffledPieces = pieces.shuffled().toMutableList()

    val tiles = (1 until gridSize * gridSize).map { number ->
        PuzzleTile(number, false, shuffledPieces[number - 1])
    }.toMutableList()

    tiles.add(PuzzleTile(0, true, null)) // Añadir la pieza vacía

    return PuzzleState(tiles)
}

fun moveTile(puzzleState: PuzzleState, index: Int): PuzzleState {
    val tiles = puzzleState.tiles.toMutableList()
    val blankIndex = tiles.indexOfFirst { it.isBlank }

    // Verificar si la pieza seleccionada es adyacente a la pieza vacía
    if (isAdjacent(index, blankIndex)) {
        // Guardamos temporalmente la imagen de la pieza seleccionada
        val selectedImage = tiles[index].imageBitmap

        // Intercambiamos la pieza vacía con la seleccionada
        tiles[blankIndex] = tiles[blankIndex].copy(imageBitmap = selectedImage, isBlank = false)
        tiles[index] = tiles[index].copy(imageBitmap = null, isBlank = true)
    }

    return PuzzleState(tiles)
}

fun isAdjacent(index: Int, blankIndex: Int): Boolean {
    val size = 4
    val row1 = index / size
    val col1 = index % size
    val row2 = blankIndex / size
    val col2 = blankIndex % size
    return (row1 == row2 && kotlin.math.abs(col1 - col2) == 1) || (col1 == col2 && kotlin.math.abs(row1 - row2) == 1)
}

