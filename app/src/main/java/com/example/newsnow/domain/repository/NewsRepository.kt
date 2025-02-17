package com.example.newsnow.domain.repository

import androidx.paging.PagingData
import com.example.newsnow.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources: List<String>): Flow<PagingData<Article>>
    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>

    fun getArticles(): Flow<List<Article>>
    suspend fun getArticle(articleId: Int?): Article?

    suspend fun deleteArticle(article: Article)
    suspend fun upsertArticle(article: Article)
}