package com.example.newsnow.domain.usecases.news

import com.example.newsnow.domain.repository.NewsRepository

class SearchNews (
    private val newsRepository: NewsRepository
){
    operator fun invoke(searchQuery: String, sources: List<String>) =
        newsRepository.searchNews(searchQuery = searchQuery, sources = sources)

}