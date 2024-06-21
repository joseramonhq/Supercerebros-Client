@file:OptIn(ExperimentalMaterial3Api::class)

package com.myapp.components.textInputComponents.sampleData

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

/**
 * Data class para representar los parámetros de un OutlinedTextField en Jetpack Compose.
 *
 * @property value El valor del texto.
 * @property onValueChange Función que se llama cuando el valor del texto cambia.
 * @property modifier Modificador para aplicar al OutlinedTextField.
 * @property enabled Indica si el OutlinedTextField está habilitado.
 * @property readOnly Indica si el OutlinedTextField es de solo lectura.
 * @property textStyle Estilo del texto dentro del OutlinedTextField.
 * @property label Etiqueta que se muestra dentro del OutlinedTextField.
 * @property placeholder Texto de marcador de posición.
 * @property leadingIcon Icono que se muestra al principio del OutlinedTextField.
 * @property trailingIcon Icono que se muestra al final del OutlinedTextField.
 * @property prefix Prefijo que se muestra dentro del OutlinedTextField.
 * @property suffix Sufijo que se muestra dentro del OutlinedTextField.
 * @property supportingText Texto de apoyo que se muestra debajo del OutlinedTextField.
 * @property isError Indica si el campo de texto está en estado de error.
 * @property visualTransformation Transformación visual del texto (por ejemplo, para contraseñas).
 * @property keyboardOptions Opciones del teclado para el OutlinedTextField.
 * @property keyboardActions Acciones del teclado para el OutlinedTextField.
 * @property singleLine Indica si el OutlinedTextField es de una sola línea.
 * @property maxLines Número máximo de líneas que puede tener el OutlinedTextField.
 * @property minLines Número mínimo de líneas que puede tener el OutlinedTextField.
 * @property interactionSource Fuente de interacción para el OutlinedTextField.
 * @property shape Forma del OutlinedTextField.
 * @property colors Colores del OutlinedTextField.
 */


data class OutlinedTextFieldData(
    var value: String = "",
    var onValueChange: (String) -> Unit = {},
    var modifier: Modifier = Modifier,
    var enabled: Boolean = true,
    var readOnly: Boolean = false,
    var textStyle: TextStyle = TextStyle.Default,
    var label: @Composable (() -> Unit)? = null,
    var placeholder: @Composable (() -> Unit)? = null,
    var leadingIcon: @Composable (() -> Unit)? = null,
    var trailingIcon: @Composable (() -> Unit)? = null,
    var prefix: @Composable (() -> Unit)? = null,
    var suffix: @Composable (() -> Unit)? = null,
    var supportingText: @Composable (() -> Unit)? = null,
    var isError: Boolean = false,
    var visualTransformation: VisualTransformation = VisualTransformation.None,
    var keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    var keyboardActions: KeyboardActions = KeyboardActions.Default,
    var singleLine: Boolean = true,
    var maxLines: Int = 1,
    var minLines: Int = 1,
    var interactionSource: MutableInteractionSource = MutableInteractionSource(),
    var shape: Shape = RoundedCornerShape(10.dp),
    var colors: TextFieldColors


)

/**
 * Composable que muestra un OutlinedTextField utilizando los parámetros de OutlinedTextFieldData.
 *
 * @param data Parámetros del OutlinedTextField encapsulados en un objeto OutlinedTextFieldData.
 */
@Composable
fun CustomOutlinedTextField(
    data: OutlinedTextFieldData,
    colors: TextFieldColors = TextFieldDefaults.colors()
) {

    OutlinedTextField(
        value = data.value,
        onValueChange = data.onValueChange,
        modifier = data.modifier,
        enabled = data.enabled,
        readOnly = data.readOnly,
        textStyle = data.textStyle,
        label = data.label,
        placeholder = data.placeholder,
        leadingIcon = data.leadingIcon,
        trailingIcon = data.trailingIcon,
        prefix = data.prefix,
        suffix = data.suffix,
        supportingText = data.supportingText,
        isError = data.isError,
        visualTransformation = data.visualTransformation,
        keyboardOptions = data.keyboardOptions,
        keyboardActions = data.keyboardActions,
        singleLine = data.singleLine,
        maxLines = data.maxLines,
        minLines = data.minLines,
        interactionSource = data.interactionSource,
        shape = data.shape,
        colors = data.colors
    )
}
