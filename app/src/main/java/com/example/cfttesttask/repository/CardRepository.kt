package com.example.cfttesttask.repository

import com.example.cfttesttask.data.local.dbo.CardInfoDbo
import com.example.cfttesttask.util.NetworkResource
import kotlinx.coroutines.flow.Flow

interface CardRepository {

    suspend fun getCardInfo(bin: String): Flow<NetworkResource<CardInfoDbo>>
}