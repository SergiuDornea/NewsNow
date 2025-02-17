package com.example.newsnow.domain.usecases.news

import com.example.newsnow.domain.repository.NewsRepository
import javax.inject.Inject

class GetArticle @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(articleId: Int?) = newsRepository.getArticle(articleId)
}