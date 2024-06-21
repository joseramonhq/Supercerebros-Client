package com.myapp.components.textInputComponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.myapp.components.textInputComponents.sampleData.CustomTextField
import com.myapp.components.textInputComponents.sampleData.TextFieldData

@Composable
fun TextFieldComponent(value: String = "", onValueChange: (String) -> Unit) {
    val text = remember {
        mutableStateOf(value)
    }
    var colors: TextFieldColors = TextFieldColors(
        focusedTextColor = Color.Transparent,
        unfocusedTextColor = Color.Transparent,
        disabledTextColor = Color.Transparent,
        errorTextColor = Color.Transparent,
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        disabledContainerColor = Color.Transparent,
        errorContainerColor = Color.Transparent,
        cursorColor = Color.Transparent,
        errorCursorColor = Color.Transparent,
        textSelectionColors = TextSelectionColors(handleColor = Color.Blue, Color.Red),
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
        focusedLeadingIconColor = Color.Transparent,
        unfocusedLeadingIconColor = Color.Transparent,
        disabledLeadingIconColor = Color.Transparent,
        errorLeadingIconColor = Color.Transparent,
        focusedTrailingIconColor = Color.Transparent,
        unfocusedTrailingIconColor = Color.Transparent,
        disabledTrailingIconColor = Color.Transparent,
        errorTrailingIconColor = Color.Transparent,
        focusedLabelColor = Color.Transparent,
        unfocusedLabelColor = Color.Transparent,
        disabledLabelColor = Color.Transparent,
        errorLabelColor = Color.Transparent,
        focusedPlaceholderColor = Color.Transparent,
        unfocusedPlaceholderColor = Color.Transparent,
        disabledPlaceholderColor = Color.Transparent,
        errorPlaceholderColor = Color.Transparent,
        focusedSupportingTextColor = Color.Transparent,
        unfocusedSupportingTextColor = Color.Transparent,
        disabledSupportingTextColor = Color.Transparent,
        errorSupportingTextColor = Color.Transparent,
        focusedPrefixColor = Color.Transparent,
        unfocusedPrefixColor = Color.Transparent,
        disabledPrefixColor = Color.Transparent,
        errorPrefixColor = Color.Transparent,
        focusedSuffixColor = Color.Transparent,
        unfocusedSuffixColor = Color.Transparent,
        disabledSuffixColor = Color.Transparent,
        errorSuffixColor = Color.Transparent


    )
    Column {

        CustomTextField(
            data = TextFieldData(
                value = text.value, onValueChange = onValueChange, colors = colors

            )
        )





        HorizontalDivider(
            color = Color.Black, // Color de la línea
            thickness = 2.dp, // Grosor de la línea
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .size(width = 100.dp, height = 2.dp)
        )
    }
}
