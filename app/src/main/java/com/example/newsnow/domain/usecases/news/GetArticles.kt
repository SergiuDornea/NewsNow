package com.example.newsnow.domain.usecases.news

import com.example.newsnow.domain.repository.NewsRepository

class GetArticles(
    private val newsRepository: NewsRepository
) {
    operator fun invoke() = newsRepository.getArticles()
}