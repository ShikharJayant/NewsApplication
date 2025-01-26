package com.loc.newsapp.di

import android.app.Application
import com.loc.newsapp.model.manager.LocalUserManagerImpl
import com.loc.newsapp.model.remote.NewsApi
import com.loc.newsapp.model.repository.NewsRepositoryImpl
import com.loc.newsapp.viewmodel.manager.LocalUserManger
import com.loc.newsapp.viewmodel.manager.usecases.app_entry.AppEntryUseCases
import com.loc.newsapp.viewmodel.manager.usecases.app_entry.ReadAppEntry
import com.loc.newsapp.viewmodel.manager.usecases.app_entry.SaveAppEntry
import com.loc.newsapp.viewmodel.manager.usecases.news.GetNews
import com.loc.newsapp.viewmodel.manager.usecases.news.GetTopHeadlines
import com.loc.newsapp.viewmodel.manager.usecases.news.NewsUseCases
import com.loc.newsapp.viewmodel.repository.NewsRepository
import com.loc.newsapp.viewmodel.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManger = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManger: LocalUserManger) =   AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )



    @Provides
    @Singleton
    fun provideNewsApi():NewsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)

    }


    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ): NewsRepository = NewsRepositoryImpl(newsApi)


    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            getTopHeadlines = GetTopHeadlines(newsRepository)

        )

    }
}