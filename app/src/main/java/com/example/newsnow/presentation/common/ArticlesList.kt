package com.example.newsnow.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.newsnow.domain.model.Article
import com.example.newsnow.presentation.Dimens.EXTRA_SMALL_PADDING
import com.example.newsnow.presentation.Dimens.MEDIUM_PADDING1

@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles: LazyPagingItems<Article>,
    onClick: (Article) -> Unit
) {
    val handlePagingResult = handlePagingResult(articles = articles)
    if (handlePagingResult) {
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING1),
            contentPadding = PaddingValues(all = EXTRA_SMALL_PADDING)
        ) {
            items(count = articles.itemCount) {
                articles[it]?.let {
                    ArticleCard(article = it, onClick = { onClick(it) })
                }
            }
        }

    }
}

@Composable
fun ArticleList(
    modifier: Modifier = Modifier,
    articles: List<Article>,
    onClick: (Article) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING1),
        contentPadding = PaddingValues(all = EXTRA_SMALL_PADDING)
    ) {
        items(count = articles.size) {
            val article = articles[it]
            ArticleCard(article = article, onClick = { onClick(article) })
        }
    }
}

@Composable
fun handlePagingResult(
    articles: LazyPagingItems<Article>,
): Boolean {
    val loadState = articles.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        else -> null
    }
    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffect()
            false
        }

        error != null -> {
            EmptyScreen(error = error)
            false
        }

        else -> {
            true
        }
    }
}

@Composable
private fun ShimmerEffect() {
    Column(verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING1)) {
        repeat(10) {
            ArticleCardShimmerEffect(
                modifier = Modifier.padding(horizontal = MEDIUM_PADDING1)
            )
        }
    }
}