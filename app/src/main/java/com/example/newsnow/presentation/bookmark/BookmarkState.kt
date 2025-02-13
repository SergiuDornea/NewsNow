package com.example.newsnow.presentation.bookmark

import com.example.newsnow.domain.model.Article

data class BookmarkState(
    val articles: List<Article> = emptyList()
)
