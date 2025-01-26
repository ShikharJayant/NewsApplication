package com.loc.newsapp.model.remote

import com.loc.newsapp.viewmodel.model1.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)