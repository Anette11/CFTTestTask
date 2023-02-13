package com.example.cfttesttask.util

import kotlinx.coroutines.CoroutineExceptionHandler

fun createCoroutineExceptionHandler(
    onError: () -> Unit
): CoroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
    onError()
}