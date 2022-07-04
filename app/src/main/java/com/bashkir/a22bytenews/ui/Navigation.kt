package com.bashkir.a22bytenews.ui

sealed class NewsScreen(val destination: String) {
    object News : NewsScreen("news")
    object Details : NewsScreen("details")
}