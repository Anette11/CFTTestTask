package com.example.cfttesttask.ui.components.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cfttesttask.R
import com.example.cfttesttask.data.local.dbo.CardInfoDbo
import com.example.cfttesttask.repository.CardRepository
import com.example.cfttesttask.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: CardRepository,
    private val resourcesProvider: ResourcesProvider
) : ViewModel() {

    private val _cardInfo: MutableStateFlow<List<Item>> = MutableStateFlow(emptyList())
    val cardInfo: StateFlow<List<Item>> = _cardInfo

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _value: MutableStateFlow<String> = MutableStateFlow("")
    val value: StateFlow<String> = _value

    fun onValueChange(newValue: String) {
        _value.value = newValue
    }

    fun onClear() {
        _value.value = ""
    }

    private val _error: MutableSharedFlow<String> = MutableSharedFlow()
    val error: SharedFlow<String> = _error

    fun getCardInfo() = viewModelScope.launch {
        repository.getCardInfo(bin = value.value)
            .collect { resource: NetworkResource<CardInfoDbo> ->
                when (resource) {
                    is NetworkResource.Loading -> _isLoading.emit(true)
                    is NetworkResource.Success -> {
                        _isLoading.emit(false)
                        val list = createCardInfo(cardInfoDbo = resource.data!!)
                        _cardInfo.emit(list)
                    }
                    is NetworkResource.Failure -> {
                        _isLoading.emit(false)
                    }
                }
            }
    }

    private fun createCardInfo(
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
                brand = cardInfoDbo.brand ?: resourcesProvider.getString(R.string.not_applicable)
            )
        )
        add(Item.Space)
        add(
            Item.CardNumber(
                length = cardInfoDbo.number?.length ?: 0,
                luhn = if (cardInfoDbo.number?.luhn == true) Luhn.Yes else Luhn.No
            )
        )
        add(Item.Space)
        add(
            Item.Type(
                type = if (cardInfoDbo.type == "debit") Type.Debit else Type.Credit
            )
        )
        add(Item.Space)
        add(
            Item.Prepaid(
                prepaid = if (cardInfoDbo.prepaid == true) Prepaid.Yes else Prepaid.No
            )
        )
        add(Item.Space)
        add(
            Item.Country(
                emoji = cardInfoDbo.country?.emoji
                    ?: resourcesProvider.getString(R.string.not_applicable),
                name = cardInfoDbo.country?.name
                    ?: resourcesProvider.getString(R.string.not_applicable),
                latitude = cardInfoDbo.country?.latitude ?: 0.0,
                longitude = cardInfoDbo.country?.longitude ?: 0.0
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