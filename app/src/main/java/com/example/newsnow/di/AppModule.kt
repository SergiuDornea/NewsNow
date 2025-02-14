package com.example.newsnow.di

import android.app.Application
import androidx.room.Room
import com.example.newsnow.data.local.NewsDao
import com.example.newsnow.data.local.NewsDatabase
import com.example.newsnow.data.local.NewsTypeConverter
import com.example.newsnow.data.manager.LocalUserManagerImpl
import com.example.newsnow.data.repository.NewsRepositoryImpl
import com.example.newsnow.domain.manager.LocalUserManager
import com.example.newsnow.domain.repository.NewsRepository
import com.example.newsnow.domain.usecases.app_entry.AppEntryUseCases
import com.example.newsnow.domain.usecases.app_entry.ReadAppEntry
import com.example.newsnow.domain.usecases.app_entry.SaveAppEntry
import com.example.newsnow.domain.usecases.news.DeleteArticle
import com.example.newsnow.domain.usecases.news.GetArticle
import com.example.newsnow.domain.usecases.news.GetArticles
import com.example.newsnow.domain.usecases.news.GetNews
import com.example.newsnow.domain.usecases.news.NewsUseCases
import com.example.newsnow.domain.usecases.news.SearchNews
import com.example.newsnow.domain.usecases.news.UpsertArticle
import com.example.newsnow.network.NewsApi
import com.example.newsnow.util.Constants.BASE_URL
import com.example.newsnow.util.Constants.LOCAL_DATABASE_NAME
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
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ): NewsRepository {
        return NewsRepositoryImpl(newsApi, newsDao)
    }

    @Provides
    @Singleton
    fun provideGetNewsUseCases(newsRepository: NewsRepository): NewsUseCases =
        NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            getArticles = GetArticles(newsRepository),
            getArticle = GetArticle(newsRepository)
        )

    @Provides
    @Singleton
    fun provideLocalDatabase(application: Application): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = LOCAL_DATABASE_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(newsDatabase: NewsDatabase): NewsDao = newsDatabase.newsDao
}