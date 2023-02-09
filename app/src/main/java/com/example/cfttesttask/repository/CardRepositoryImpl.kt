package com.example.cfttesttask.repository

import com.example.cfttesttask.R
import com.example.cfttesttask.data.local.CardDao
import com.example.cfttesttask.data.local.dbo.CardInfoDbo
import com.example.cfttesttask.data.mappers.toCardInfoDbo
import com.example.cfttesttask.data.remote.CardApi
import com.example.cfttesttask.util.CoroutineDispatchersProvider
import com.example.cfttesttask.util.NetworkResource
import com.example.cfttesttask.util.ResourcesProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val api: CardApi,
    private val dao: CardDao,
    private val resourcesProvider: ResourcesProvider,
    private val coroutineDispatchersProvider: CoroutineDispatchersProvider
) : CardRepository {

    override suspend fun getCardInfo(
        bin: String
    ): Flow<NetworkResource<CardInfoDbo>> = flow {
        emit(NetworkResource.Loading())
        try {
            val response = api.getCardInfo(bin = bin)
            if (response.isSuccessful && response.body() != null) {
                val cardInfoDto = response.body()!!
                dao.saveCardInfo(cardInfoDbo = cardInfoDto.toCardInfoDbo(bin = bin))
                val cardInfoDbo = dao.getCardInfo(bin = bin)
                emit(NetworkResource.Success(data = cardInfoDbo))
            } else {
                emit(NetworkResource.Failure(message = resourcesProvider.getString(R.string.generic_error)))
            }
        } catch (e: Exception) {
            emit(NetworkResource.Failure(message = resourcesProvider.getString(R.string.generic_error)))
        }
    }.flowOn(coroutineDispatchersProvider.io)
}