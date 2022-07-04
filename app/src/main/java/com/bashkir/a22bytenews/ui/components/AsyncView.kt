package com.bashkir.a22bytenews.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.airbnb.mvrx.*
import com.bashkir.a22bytenews.ui.theme.titleText
import com.bashkir.a22bytenews.ui.theme.titleTextSize

@Composable
fun <T> AsyncView(
    async: Async<T>,
    onUpdate: () -> Unit,
    onSuccess: @Composable (T, Boolean) -> Unit
) =
    when (async) {
        is Success -> async().let { element ->
            if (element is List<*> && element.isEmpty())
                NoElementsView()
            else
                onSuccess(element, false)
        }
        is Loading ->
            async().let { data ->
                if (data == null || data is List<*> && data.isEmpty())
                    LoadingView()
                else
                    onSuccess(data, true)
            }
        is Fail -> ErrorView("Не удалось загрузить данные :(", async, onUpdate)
        Uninitialized -> ErrorView("Не удалось отправить запрос :(")
    }

@Composable
private fun NoElementsView() =
    Box(Modifier.fillMaxSize()) {
        Text(
            "По вашему запросу ничего не найдено :(",
            Modifier.align(Alignment.Center)
        )
    }

@Composable
private fun LoadingView() = Box(modifier = Modifier.fillMaxSize()) {
    CircularProgressIndicator(
        modifier = Modifier
            .wrapContentSize()
            .align(Alignment.Center)
    )
}

@Composable
private fun ErrorView(text: String, asyncFail: Fail<*>? = null, onUpdate: () -> Unit = {}) =
    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .align(Alignment.Center)
                .fillMaxWidth()) {
            Text(
                "$text\n${asyncFail?.error?.message ?: ""}",
                color = Color.Red,
                fontSize = titleTextSize,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            TextButton(
                onClick = onUpdate,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ){
                Text("Обновить", style = titleText)
            }
        }
    }