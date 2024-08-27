package com.supercerebros.screens.puzzle


import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.supercerebros.R

@Composable
fun PuzzleGame(context: Context, resourceId: Int) {
    var puzzleState by remember { mutableStateOf(generateInitialPuzzleState(context, resourceId)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray), // Fondo del puzzle
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PuzzleGrid(puzzleState.tiles) { index ->
            puzzleState = moveTile(puzzleState, index)
        }
    }
}

@Preview
@Composable
fun PuzzlePreview() {
    val context = LocalContext.current
    PuzzleGame(context = context, resourceId = R.drawable.eyes_screen)
}
