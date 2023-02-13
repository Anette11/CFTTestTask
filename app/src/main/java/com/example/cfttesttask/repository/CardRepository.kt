package com.example.cfttesttask.repository

import com.example.cfttesttask.data.local.dbo.CardInfoDbo
import com.example.cfttesttask.util.Item
import com.example.cfttesttask.util.NetworkResource
import kotlinx.coroutines.flow.Flow

interface CardRepository {

    suspend fun getCardInfo(bin: String): Flow<NetworkResource<CardInfoDbo>>

    fun getAllCardInfos(): Flow<NetworkResource<List<CardInfoDbo>>>

    suspend fun clearAllCardInfos()

    fun createCardInfo(cardInfoDbo: CardInfoDbo): List<Item>
}