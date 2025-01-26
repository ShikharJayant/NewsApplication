package com.loc.newsapp.viewmodel.manager.usecases.news

import androidx.paging.PagingData
import com.loc.newsapp.viewmodel.model1.Article
import com.loc.newsapp.viewmodel.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews (private val newsRepository: NewsRepository){

    operator fun invoke(sources: List<String>): Flow<PagingData<Article>>{
        return newsRepository.getNews(sources = sources)
    }


}