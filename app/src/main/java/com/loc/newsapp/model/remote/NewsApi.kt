package com.loc.newsapp.model.remote


import com.loc.newsapp.viewmodel.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {


    @GET("everything")
    suspend fun getNews(
        @Query("page") page:Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse


    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String ,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse


}