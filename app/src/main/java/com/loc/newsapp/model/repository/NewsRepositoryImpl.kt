package com.loc.newsapp.model.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.loc.newsapp.model.remote.NewsApi
import com.loc.newsapp.model.remote.NewsPagingSource
import com.loc.newsapp.viewmodel.model1.Article
import com.loc.newsapp.viewmodel.repository.NewsRepository
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.rx3.rxSingle

class NewsRepositoryImpl(
    private val newsApi: NewsApi
):NewsRepository {


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

    override fun getTopHeadlinesFromMultipleSources(): Single<List<Article>> {
        return Single.defer {
            rxSingle {
                val indiaHeadlines = newsApi.getTopHeadlines("in")
                val usHeadlines = newsApi.getTopHeadlines("us")

                val articles = mutableListOf<Article>()
                articles.addAll(indiaHeadlines.articles)
                articles.addAll(usHeadlines.articles)
                articles
            }
        }
    }

}