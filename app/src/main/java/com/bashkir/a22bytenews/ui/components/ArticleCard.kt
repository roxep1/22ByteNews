package com.bashkir.a22bytenews.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.SubcomposeAsyncImage
import com.bashkir.a22bytenews.data.models.Article
import com.bashkir.a22bytenews.ui.theme.*
import com.bashkir.a22bytenews.utils.formatToString
import java.time.LocalDateTime

@Composable
fun ArticleCard(article: Article) =
    Card(
        Modifier
            .fillMaxWidth()
            .padding(articlePadding),
        elevation = normalElevation,
        shape = cardShape
    ) {
        Column(
            Modifier
                .padding(normalPadding)
                .fillMaxWidth()
        ) {
            article.img?.let { img ->
                SubcomposeAsyncImage(
                    model = img,
                    contentDescription = null,
                    modifier = Modifier.align(CenterHorizontally),
                    loading = { CircularProgressIndicator() },
                    contentScale = ContentScale.Crop
                )
            }
            SelectionContainer {
                Column {
                    Text(
                        article.title,
                        Modifier.padding(vertical = articlePadding),
                        style = titleText
                    )
                    Text(article.description, style = normalText)
                    SourceText(source = article.url)
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        if (article.author != null) Text(
                            "Автор: ${article.author}",
                            style = graySmallText
                        ) else Spacer(Modifier)

                        Text(article.publishedAt.formatToString(), style = graySmallText)
                    }
                }
            }
        }
    }

@Preview
@Composable
private fun ArticleCardPreview() =
    ArticleCard(
        article = Article(
            "Philipp Bashkir",
            "Наводнение",
            "Произошло наводнение",
            "https://i0.wp.com/electrek.co/wp-content/uploads/sites/3/2019/10/Tesla-Autopilot-hero-4-e1570845324247.jpg?resize=1200%2C628&quality=82&strip=all&ssl=1",
            "http://www.madshrimps.be/news/item/215814",
            LocalDateTime.now()
        )
    )