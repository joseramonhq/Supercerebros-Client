package com.myapp.components.textComponents

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.myapp.components.textComponents.sampledata.CustomText
import com.myapp.components.textComponents.sampledata.TextData

@Composable
fun H1Text(text: String) {
    CustomText(data = TextData(text = text), style = MaterialTheme.typography.titleLarge)

}

@Composable
fun H3Text(text: String) {
    CustomText(data = TextData(text = text), style = MaterialTheme.typography.titleMedium)

}

@Composable
fun H5Text(text: String) {
    CustomText(data = TextData(text = text), style = MaterialTheme.typography.titleSmall)

}

