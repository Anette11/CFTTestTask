package com.example.cfttesttask.di

import android.content.Context
import androidx.room.Room
import com.example.cfttesttask.data.local.CardDao
import com.example.cfttesttask.data.local.CardDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCardDatabase(
        @ApplicationContext context: Context
    ): CardDatabase = Room.databaseBuilder(
        context = context,
        klass = CardDatabase::class.java,
        name = CardDatabase.name
    ).build()

    @Provides
    @Singleton
    fun provideCardDao(
        cardDatabase: CardDatabase
    ): CardDao = cardDatabase.dao
}