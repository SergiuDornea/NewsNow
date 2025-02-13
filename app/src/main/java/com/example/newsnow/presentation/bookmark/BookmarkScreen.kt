package com.example.newsnow.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.newsnow.R
import com.example.newsnow.presentation.Dimens.MEDIUM_PADDING1
import com.example.newsnow.presentation.common.ArticleList
import com.example.newsnow.presentation.navigation.Route

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigate: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                top = MEDIUM_PADDING1,
                start = MEDIUM_PADDING1,
                end = MEDIUM_PADDING1
            )
    ) {
        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.text_medium)
        )

        Spacer(modifier = Modifier.height(MEDIUM_PADDING1))

        ArticleList(
            modifier = Modifier,
            articles = state.articles,
            onClick = { navigate(Route.DetailsScreen.route) })
    }
}