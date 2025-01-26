package com.example.newsnow.di

import android.app.Application
import com.example.newsnow.data.manager.LocalUserManagerImpl
import com.example.newsnow.data.repository.NewsRepositoryImpl
import com.example.newsnow.domain.manager.LocalUserManager
import com.example.newsnow.domain.repository.NewsRepository
import com.example.newsnow.domain.usecases.app_entry.AppEntryUseCases
import com.example.newsnow.domain.usecases.app_entry.ReadAppEntry
import com.example.newsnow.domain.usecases.app_entry.SaveAppEntry
import com.example.newsnow.domain.usecases.news.GetNews
import com.example.newsnow.domain.usecases.news.NewsUseCases
import com.example.newsnow.network.NewsApi
import com.example.newsnow.util.Constants.BASE_URL
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
    fun provideLocalUserManager(application: Application): LocalUserManager =
        LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager): AppEntryUseCases =
        AppEntryUseCases(
            readAppEntry = ReadAppEntry(localUserManager),
            saveAppEntry = SaveAppEntry(localUserManager)
        )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository {
        return NewsRepositoryImpl(newsApi)
    }

    @Provides
    @Singleton
    fun provideGetNewsUseCases(newsRepository: NewsRepository): NewsUseCases =
        NewsUseCases(getNews = GetNews(newsRepository))
}