package com.myapp.components.textComponents.sampledata

import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit

/**
 * Data class que encapsula los parámetros para el composable Text.
 *
 * @param text El texto que se mostrará.
 * @param modifier Modificador opcional para personalizar el diseño del Text.
 * @param color Color del texto.
 * @param fontSize Tamaño de la fuente del texto.
 * @param fontStyle Estilo de la fuente (normal, itálico, etc.).
 * @param fontWeight Grosor de la fuente (normal, negrita, etc.).
 * @param fontFamily Familia de fuentes para el texto.
 * @param letterSpacing Espaciado entre caracteres.
 * @param textDecoration Decoración del texto (subrayado, tachado, etc.).
 * @param textAlign Alineación del texto.
 * @param lineHeight Altura de línea del texto.
 * @param overflow Manejo del desbordamiento de texto.
 * @param softWrap Indica si el texto debe envolverse automáticamente.
 * @param maxLines Número máximo de líneas para el texto.
 * @param onTextLayout Lambda para manejar el resultado del diseño del texto.
 * @param style Estilo de texto personalizado.
 */
data class TextData(
    val text: String,
    val modifier: Modifier = Modifier,
    val color: Color = Color.Unspecified,
    val fontSize: TextUnit = TextUnit.Unspecified,
    val fontStyle: FontStyle? = null,
    val fontWeight: FontWeight? = null,
    val fontFamily: FontFamily? = null,
    val letterSpacing: TextUnit = TextUnit.Unspecified,
    val textDecoration: TextDecoration? = null,
    val textAlign: TextAlign? = null,
    val lineHeight: TextUnit = TextUnit.Unspecified,
    val overflow: TextOverflow = TextOverflow.Clip,
    val softWrap: Boolean = true,
    val maxLines: Int = Int.MAX_VALUE,
    val onTextLayout: (TextLayoutResult) -> Unit = {},

    )


/**
 * Composable que muestra texto con opciones configurables.
 *
 * @param data Objeto TextData que contiene los parámetros para configurar el texto.
 */
@Composable
fun CustomText(data: TextData, style: TextStyle = LocalTextStyle.current) {
    Text(
        text = data.text,
        modifier = data.modifier,
        color = data.color,
        fontSize = data.fontSize,
        fontStyle = data.fontStyle,
        fontWeight = data.fontWeight,
        fontFamily = data.fontFamily,
        letterSpacing = data.letterSpacing,
        textDecoration = data.textDecoration,
        textAlign = data.textAlign,
        lineHeight = data.lineHeight,
        overflow = data.overflow,
        softWrap = data.softWrap,
        maxLines = data.maxLines,
        onTextLayout = data.onTextLayout,
        style = style
    )
}