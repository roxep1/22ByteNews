package com.bashkir.a22bytenews.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.airbnb.mvrx.Fail
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.Uninitialized
import com.airbnb.mvrx.compose.collectAsState
import com.bashkir.a22bytenews.data.NewsViewModel
import com.bashkir.a22bytenews.data.models.Article
import com.bashkir.a22bytenews.ui.components.ArticleCard
import com.bashkir.a22bytenews.ui.components.AsyncView
import com.bashkir.a22bytenews.ui.components.SearchTextField
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun NewsScreen(viewModel: NewsViewModel) {
    val searchText = remember { mutableStateOf(TextFieldValue()) }

    LaunchedEffect(true) {
        viewModel.getArticles()
    }

    Scaffold(
        topBar = {
            SearchTextField(
                searchTextState = searchText,
                onSearch = viewModel::getArticles
            )
        }
    ) { paddingValues ->
        val articlesAsync by viewModel.collectAsState { it.articles }

        AsyncView(async = articlesAsync, onUpdate = viewModel::getArticles) { articles, isLoading ->
            SwipeRefresh(
                state = rememberSwipeRefreshState(isLoading),
                onRefresh = viewModel::getArticles
            ) {
                LazyColumn(
                    Modifier
                        .fillMaxWidth()
                        .padding(paddingValues)
                ) {
                    items(articles) { item ->
                        ArticleCard(article = item)
                    }
                }
            }
        }
    }
}