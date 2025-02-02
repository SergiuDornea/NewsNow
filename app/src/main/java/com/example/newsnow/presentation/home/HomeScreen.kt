package com.example.newsnow.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.paging.compose.LazyPagingItems
import com.example.newsnow.R
import com.example.newsnow.domain.model.Article
import com.example.newsnow.presentation.Dimens.MEDIUM_PADDING1

@Composable
fun HomeScreen(articles: LazyPagingItems<Article>, navigate: (String) -> Unit) {
    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = "\u2022") { it.title }
            } else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = MEDIUM_PADDING1)
            .statusBarsPadding()
    ) {
        Image(painter = painterResource(id = R.drawable.ic_logo), contentDescription = "Logo")
    }
}