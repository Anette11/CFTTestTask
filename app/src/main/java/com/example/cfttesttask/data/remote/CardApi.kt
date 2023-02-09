package com.example.cfttesttask.data.remote

import com.example.cfttesttask.data.remote.dto.CardInfoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CardApi {

    companion object {
        const val baseUrl = "https://lookup.binlist.net/"
    }

    @GET("{bin}")
    suspend fun getCardInfo(@Path("bin") bin: String): Response<CardInfoDto>
}