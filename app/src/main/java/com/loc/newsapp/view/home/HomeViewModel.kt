package com.loc.newsapp.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.loc.newsapp.viewmodel.manager.usecases.news.NewsUseCases
import com.loc.newsapp.viewmodel.model1.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
): ViewModel() {

    val news = newsUseCases.getNews(
        sources =  listOf("bbc-news","abc-news","techcrunch","the-hindu","cnn","the-verge","cnbc","business-insider,the-new-york-times")
    ).cachedIn(viewModelScope)

    private val _topHeadlines = MutableStateFlow<List<Article>>(emptyList())
    val topHeadlines: StateFlow<List<Article>> = _topHeadlines.asStateFlow()

    init {
        fetchTopHeadlines()
    }

    private fun fetchTopHeadlines() {
        newsUseCases.getTopHeadlines()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ articles ->

                _topHeadlines.value = articles
            }, { error ->
                // Handle error (optional: log or show error message)
                error.printStackTrace()
            })
    }
}