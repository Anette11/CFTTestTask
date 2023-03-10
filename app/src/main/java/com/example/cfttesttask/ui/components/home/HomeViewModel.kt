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
    private val resourcesProvider: ResourcesProvider,
    private val coroutineDispatchersProvider: CoroutineDispatchersProvider
) : ViewModel() {

    private val _cardInfo: MutableStateFlow<List<Item>> = MutableStateFlow(emptyList())
    val cardInfo: StateFlow<List<Item>> = _cardInfo

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _value: MutableStateFlow<String> =
        MutableStateFlow(resourcesProvider.getString(R.string.empty))
    val value: StateFlow<String> = _value

    fun onValueChange(newValue: String) {
        _value.value = newValue
    }

    fun onClear() {
        _value.value = resourcesProvider.getString(R.string.empty)
    }

    fun isEnable(): Boolean = _value.value.isNotEmpty()

    private val _error: MutableSharedFlow<String> = MutableSharedFlow()
    val error: SharedFlow<String> = _error

    fun getCardInfo() = viewModelScope.launch(
        createCoroutineExceptionHandler(
            onError = {
                viewModelScope.launch(coroutineDispatchersProvider.io) {
                    _isLoading.emit(false)
                    _error.emit(resourcesProvider.getString(R.string.generic_error))
                }
            }
        )
    ) {
        repository.getCardInfo(bin = value.value)
            .collect { resource: NetworkResource<CardInfoDbo> ->
                when (resource) {
                    is NetworkResource.Loading -> {
                        _isLoading.emit(true)
                        _cardInfo.emit(emptyList())
                    }
                    is NetworkResource.Success -> {
                        _isLoading.emit(false)
                        val list = repository.createCardInfo(cardInfoDbo = resource.data!!)
                        _cardInfo.emit(list)
                    }
                    is NetworkResource.Failure -> {
                        _isLoading.emit(false)
                        _error.emit(
                            resource.message ?: resourcesProvider.getString(R.string.generic_error)
                        )
                    }
                }
            }
    }
}