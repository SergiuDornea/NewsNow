package com.example.newsnow.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsnow.present.SearchBar
import com.example.newsnow.presentation.Dimens.MEDIUM_PADDING1
import com.example.newsnow.presentation.Dimens.MEDIUM_PADDING3
import com.example.newsnow.presentation.common.ArticleList

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigate: (String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MEDIUM_PADDING3, start = MEDIUM_PADDING1, end = MEDIUM_PADDING1)
    ) {
        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateSearchQuery(it)) },
            onSearch = {
                event(SearchEvent.SearchNews)
            }
        )
        state.article?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticleList(articles = articles, onClick = {})
        }
    }
}