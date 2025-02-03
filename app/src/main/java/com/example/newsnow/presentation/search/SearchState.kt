package com.example.newsnow.presentation.search

import androidx.paging.PagingData
import com.example.newsnow.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    var article: Flow<PagingData<Article>>? = null
)