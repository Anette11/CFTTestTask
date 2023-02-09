package com.example.cfttesttask.di

import com.example.cfttesttask.util.CoroutineDispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoroutineModule {

    @Provides
    @Singleton
    fun provideCoroutineDispatchersProvider(): CoroutineDispatchersProvider =
        object : CoroutineDispatchersProvider {
            override val io: CoroutineDispatcher
                get() = Dispatchers.IO
        }
}