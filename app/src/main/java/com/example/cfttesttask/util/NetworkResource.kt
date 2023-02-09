package com.example.cfttesttask.util

sealed class NetworkResource<T>(
    val data: T?,
    val message: String?
) {
    class Success<T>(
        data: T
    ) : NetworkResource<T>(
        data = data,
        message = null
    )

    class Failure<T>(
        message: String
    ) : NetworkResource<T>(
        data = null,
        message = message
    )

    class Loading<T>
        : NetworkResource<T>(
        data = null,
        message = null
    )

    class Empty<T>
        : NetworkResource<T>(
        data = null,
        message = null
    )
}