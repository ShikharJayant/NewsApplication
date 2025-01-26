package com.loc.newsapp.viewmodel.manager.usecases.news

import com.loc.newsapp.viewmodel.model1.Article
import com.loc.newsapp.viewmodel.repository.NewsRepository
import io.reactivex.rxjava3.core.Single

class GetTopHeadlines(
    private val repository: NewsRepository
) {
    operator fun invoke(): Single<List<Article>> {
        return repository.getTopHeadlinesFromMultipleSources()
    }
}
