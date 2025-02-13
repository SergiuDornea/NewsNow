package com.example.newsnow.domain.usecases.news

import com.example.newsnow.domain.model.Article
import com.example.newsnow.domain.repository.NewsRepository

class UpsertArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(article: Article) = newsRepository.upsertArticle(article = article)
}