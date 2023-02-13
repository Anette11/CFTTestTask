package com.example.cfttesttask.repository

import com.example.cfttesttask.R
import com.example.cfttesttask.data.local.CardDao
import com.example.cfttesttask.data.local.dbo.CardInfoDbo
import com.example.cfttesttask.data.mappers.toCardInfoDbo
import com.example.cfttesttask.data.remote.CardApi
import com.example.cfttesttask.util.*
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
    ): Flow<NetworkResource<CardInfoDbo>> = flow<NetworkResource<CardInfoDbo>> {
        emit(NetworkResource.Loading())
        try {
            val oldCardInfoDbo = dao.getCardInfo(bin = bin)
            oldCardInfoDbo?.let {
                emit(NetworkResource.Success(data = oldCardInfoDbo))
                emit(NetworkResource.Loading())
            }
            val response = api.getCardInfo(bin = bin)
            if (response.isSuccessful && response.body() != null) {
                val cardInfoDto = response.body()!!
                dao.saveCardInfo(cardInfoDbo = cardInfoDto.toCardInfoDbo(bin = bin))
                val newCardInfoDbo = dao.getCardInfo(bin = bin)
                if (newCardInfoDbo != null) {
                    emit(NetworkResource.Success(data = newCardInfoDbo))
                } else {
                    emit(NetworkResource.Failure(message = resourcesProvider.getString(R.string.generic_error)))
                }
            } else {
                emit(NetworkResource.Failure(message = resourcesProvider.getString(R.string.generic_error)))
            }
        } catch (e: Exception) {
            emit(NetworkResource.Failure(message = resourcesProvider.getString(R.string.generic_error)))
        }
    }.flowOn(coroutineDispatchersProvider.io)

    override fun getAllCardInfos(): Flow<NetworkResource<List<CardInfoDbo>>> = flow {
        emit(NetworkResource.Loading())
        val allCardInfos = dao.getAllCardInfos()
        emit(NetworkResource.Success(allCardInfos))
    }.flowOn(coroutineDispatchersProvider.io)

    override suspend fun clearAllCardInfos() = dao.clearAllCardInfos()

    override fun createCardInfo(
        cardInfoDbo: CardInfoDbo
    ): List<Item> = buildList {
        add(
            Item.SchemeNetwork(
                schemeNetwork = cardInfoDbo.scheme
                    ?: resourcesProvider.getString(R.string.not_applicable)
            )
        )
        add(Item.Space)
        add(
            Item.Brand(
                brand = cardInfoDbo.brand
                    ?: resourcesProvider.getString(R.string.not_applicable)
            )
        )
        add(Item.Space)
        add(
            Item.CardNumber(
                length = (cardInfoDbo.number?.length
                    ?: resourcesProvider.getString(R.string.not_applicable)).toString(),
                luhn = when (cardInfoDbo.number?.luhn) {
                    true -> Luhn.Yes
                    false -> Luhn.No
                    else -> Luhn.Unknown
                }
            )
        )
        add(Item.Space)
        add(
            Item.Type(
                type = when (cardInfoDbo.type) {
                    resourcesProvider.getString(R.string.item_subtitle_type_debit)
                        .lowercase() -> Type.Debit
                    resourcesProvider.getString(R.string.item_subtitle_type_credit)
                        .lowercase() -> Type.Credit
                    else -> Type.Unknown
                }
            )
        )
        add(Item.Space)
        add(
            Item.Prepaid(
                prepaid = when (cardInfoDbo.prepaid) {
                    true -> Prepaid.Yes
                    false -> Prepaid.No
                    else -> Prepaid.Unknown
                }
            )
        )
        add(Item.Space)
        add(
            Item.Country(
                emoji = cardInfoDbo.country?.emoji
                    ?: resourcesProvider.getString(R.string.not_applicable),
                name = cardInfoDbo.country?.name
                    ?: resourcesProvider.getString(R.string.not_applicable),
                latitude = (cardInfoDbo.country?.latitude
                    ?: resourcesProvider.getString(R.string.not_applicable)).toString(),
                longitude = (cardInfoDbo.country?.longitude
                    ?: resourcesProvider.getString(R.string.not_applicable)).toString()
            )
        )
        add(Item.Space)
        add(
            Item.Bank(
                name = cardInfoDbo.bank?.name
                    ?: resourcesProvider.getString(R.string.not_applicable),
                city = cardInfoDbo.bank?.city
                    ?: resourcesProvider.getString(R.string.not_applicable),
                url = cardInfoDbo.bank?.url
                    ?: resourcesProvider.getString(R.string.not_applicable),
                phone = cardInfoDbo.bank?.phone
                    ?: resourcesProvider.getString(R.string.not_applicable)
            )
        )
    }
}