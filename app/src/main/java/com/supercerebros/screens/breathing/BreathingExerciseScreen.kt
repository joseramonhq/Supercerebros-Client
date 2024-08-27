package com.supercerebros.screens.breathing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreathingExerciseScreen() {
    var inhaleDuration by remember { mutableStateOf(4) }
    var holdBreathDuration by remember { mutableStateOf(0) }
    var exhaleDuration by remember { mutableStateOf(4) }
    var pauseDuration by remember { mutableStateOf(0) }
    var totalDuration by remember { mutableStateOf(5) }
    var vibrateEnabled by remember { mutableStateOf(true) }
    var soundEnabled by remember { mutableStateOf(true) }
    var interactiveMode by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Respiración Equilibrada") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    DurationSelector("Inhalar (segundos)", inhaleDuration, { if (inhaleDuration > 0) inhaleDuration-- }, { if (inhaleDuration < 60) inhaleDuration++ })
                    DurationSelector("Retener (segundos)", holdBreathDuration, { if (holdBreathDuration > 0) holdBreathDuration-- }, { if (holdBreathDuration < 60) holdBreathDuration++ })
                    DurationSelector("Exhalar (segundos)", exhaleDuration, { if (exhaleDuration > 0) exhaleDuration-- }, { if (exhaleDuration < 60) exhaleDuration++ })
                    DurationSelector("Pausa (segundos)", pauseDuration, { if (pauseDuration > 0) pauseDuration-- }, { if (pauseDuration < 60) pauseDuration++ })
                }
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    TotalDurationSelector("Duración (minutos)", totalDuration, { if (totalDuration > 1) totalDuration-- }, { if (totalDuration < 60) totalDuration++ })
                    OptionSelector("Vibrar", vibrateEnabled) { vibrateEnabled = it }
                    OptionSelector("Sonido", soundEnabled) { soundEnabled = it }
                    OptionSelector("Interactivo", interactiveMode) { interactiveMode = it }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            FilledTonalButton(onClick = { /* Start the breathing exercise */ }) {
                Text("Comenzar")
            }
        }
    }
}

@Composable
fun DurationSelector(label: String, value: Int, onDecrease: () -> Unit, onIncrease: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(label, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onDecrease) {
                Text("-")
            }
            Text("$value s", fontSize = 18.sp)
            IconButton(onClick = onIncrease) {
                Text("+")
            }
        }
    }
}

@Composable
fun TotalDurationSelector(label: String, duration: Int, onDecrease: () -> Unit, onIncrease: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(label, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onDecrease) {
                Text("-")
            }
            Text("$duration min", fontSize = 18.sp)
            IconButton(onClick = onIncrease) {
                Text("+")
            }
        }
    }
}


@Composable
fun DropdownMenu(selectedValue: Int, onValueChange: (Int) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf(1, 3, 5, 10, 15)

    Box {
        TextButton(onClick = { expanded = true }) {
            Text("$selectedValue minutos")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { value ->
                DropdownMenuItem(
                    text = { Text("$value minutos") },
                    onClick = {
                        onValueChange(value)
                        expanded = false
                    }
                )
            }
        }
    }
}


@Composable
fun OptionSelector(label: String, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(label, fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Switch(checked = isChecked, onCheckedChange = onCheckedChange)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BreathingExerciseScreen()
}