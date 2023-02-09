package com.example.cfttesttask.util

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatchersProvider {

    val io: CoroutineDispatcher
}