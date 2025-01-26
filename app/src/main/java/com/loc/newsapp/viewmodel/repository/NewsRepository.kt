package com.loc.newsapp.viewmodel.repository

import androidx.paging.PagingData
import com.loc.newsapp.viewmodel.model1.Article
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources: List<String>): Flow<PagingData<Article>>

    fun getTopHeadlinesFromMultipleSources(): Single<List<Article>>

}