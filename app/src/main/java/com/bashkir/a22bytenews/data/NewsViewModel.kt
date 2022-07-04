package com.bashkir.a22bytenews.data

import com.airbnb.mvrx.*
import com.bashkir.a22bytenews.data.models.Article
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

class NewsViewModel(
    initialState: NewsState,
    private val api: NewsApi
) : MavericksViewModel<NewsState>(initialState) {
    companion object : MavericksViewModelFactory<NewsViewModel, NewsState>, KoinComponent {
        override fun create(viewModelContext: ViewModelContext, state: NewsState): NewsViewModel =
            get { parametersOf(state) }
    }

    fun getArticles() = suspend {
        api.getTopArticles().articles
    }.execute(retainValue = NewsState::articles) { copy(articles = it) }

    fun getArticles(query: String) = suspend {
        api.getArticles(query).articles
    }.execute(retainValue = NewsState::articles) { copy(articles = it) }
}

data class NewsState(val articles: Async<List<Article>> = Uninitialized) : MavericksState