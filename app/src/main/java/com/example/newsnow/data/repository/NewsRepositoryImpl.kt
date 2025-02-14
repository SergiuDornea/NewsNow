package com.example.newsnow.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsnow.data.local.NewsDao
import com.example.newsnow.domain.model.Article
import com.example.newsnow.domain.repository.NewsRepository
import com.example.newsnow.network.NewsApi
import com.example.newsnow.network.NewsPagingSource
import com.example.newsnow.network.SearchNewsPagingSource
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
) :
    NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun searchNews(
        searchQuery: String,
        sources: List<String>
    ): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    newsApi = newsApi,
                    searchQuery = searchQuery,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun getArticles(): Flow<List<Article>> {
        return newsDao.getArticles()
    }

    override fun getArticle(articleId: Int?): Article? {
        return newsDao.getArticles(articleId)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    override suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article)
    }
}