package com.myapp.components.textInputComponents.sampleData

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

/**
 * Data class que representa los parámetros de un TextField en Jetpack Compose.
 *
 * @param value El valor del texto.
 * @param onValueChange Función que se llama cuando el valor del texto cambia.
 * @param modifier Modificador para aplicar al TextField.
 * @param enabled Indica si el TextField está habilitado para la interacción del usuario.
 * @param readOnly Indica si el TextField es de solo lectura.
 * @param textStyle Estilo del texto dentro del TextField.
 * @param label Composable que define la etiqueta del TextField.
 * @param placeholder Composable que define el texto de marcador de posición del TextField.
 * @param leadingIcon Composable que define el icono que aparece al principio del TextField.
 * @param trailingIcon Composable que define el icono que aparece al final del TextField.
 * @param prefix Composable que define el prefijo del TextField.
 * @param suffix Composable que define el sufijo del TextField.
 * @param supportingText Composable que define el texto de soporte debajo del TextField.
 * @param isError Indica si el TextField está en estado de error.
 * @param visualTransformation Transformación visual del texto dentro del TextField (por ejemplo, para contraseñas).
 * @param keyboardOptions Opciones del teclado para el TextField.
 * @param keyboardActions Acciones del teclado para el TextField.
 * @param singleLine Indica si el TextField es de una sola línea.
 * @param maxLines Número máximo de líneas que puede tener el TextField.
 * @param minLines Número mínimo de líneas que puede tener el TextField.
 * @param interactionSource Fuente de interacción para el TextField.
 * @param shape Forma del TextField.
 * @param colors Composable que define los colores del TextField.
 */
data class TextFieldData(
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
    var singleLine: Boolean = false,
    var maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    var minLines: Int = 1,
    var interactionSource: MutableInteractionSource? = null,
    var shape: Shape = RoundedCornerShape(10.dp),
    var colors: TextFieldColors
)

/**
 * Función @Composable que crea un TextField utilizando los datos de TextFieldData.
 *
 * @param data Objeto TextFieldData que contiene todos los parámetros necesarios para el TextField.
 */
@Composable
fun CustomTextField(data: TextFieldData, colors: TextFieldColors = TextFieldDefaults.colors()) {
    TextField(
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
        interactionSource = remember { MutableInteractionSource() },
        shape = data.shape,
        colors = colors
    )
}