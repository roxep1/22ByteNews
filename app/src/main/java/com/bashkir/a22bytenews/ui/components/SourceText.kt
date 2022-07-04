package com.bashkir.a22bytenews.ui.components

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import com.bashkir.a22bytenews.ui.theme.normalTextSize

@Composable
fun SourceText(source: String, modifier: Modifier = Modifier) {
    val annotatedLinkString: AnnotatedString = buildAnnotatedString {

        val str = "Источник"
        append(str)
        addStyle(
            style = SpanStyle(
                color = Color(0xff64B5F6),
                fontSize = normalTextSize,
                textDecoration = TextDecoration.Underline
            ), start = 0, end = str.lastIndex + 1
        )

        addStringAnnotation(
            tag = "URL",
            annotation = source,
            start = 0,
            end = str.lastIndex + 1
        )
    }
    val uriHandler = LocalUriHandler.current

    ClickableText(
        modifier = modifier,
        text = annotatedLinkString,
        onClick = {
            annotatedLinkString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        }
    )
}