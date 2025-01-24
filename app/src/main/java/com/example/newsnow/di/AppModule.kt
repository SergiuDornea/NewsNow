package com.example.newsnow.di

import android.app.Application
import com.example.newsnow.data.manager.LocalUserManagerImpl
import com.example.newsnow.domain.manager.LocalUserManager
import com.example.newsnow.domain.usecases.AppEntryUseCases
import com.example.newsnow.domain.usecases.ReadAppEntry
import com.example.newsnow.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
}