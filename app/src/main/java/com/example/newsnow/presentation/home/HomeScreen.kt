package com.example.newsnow.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.example.newsnow.R
import com.example.newsnow.domain.model.Article
import com.example.newsnow.present.SearchBar
import com.example.newsnow.presentation.Dimens.MEDIUM_PADDING1
import com.example.newsnow.presentation.common.ArticleList

@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Article) -> Unit
) {
    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83D\uDFE5 ") { it.title }
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
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Logo", modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = MEDIUM_PADDING1)
        )
        Spacer(modifier = Modifier.height(MEDIUM_PADDING1))

        SearchBar(
            modifier = Modifier
                .padding(horizontal = MEDIUM_PADDING1)
                .fillMaxWidth(),
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick = { navigateToSearch() },
            onSearch = {},
        )
        Spacer(modifier = Modifier.height(MEDIUM_PADDING1))

        Text(
            text = titles,
            modifier = Modifier
                .padding(horizontal = MEDIUM_PADDING1)
                .basicMarquee()
                .fillMaxWidth(),
            fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )
        Spacer(modifier = Modifier.height(MEDIUM_PADDING1))

        ArticleList(
            modifier = Modifier.padding(horizontal = MEDIUM_PADDING1),
            articles = articles,
            onClick = { navigateToDetails(it) }
        )
    }
}