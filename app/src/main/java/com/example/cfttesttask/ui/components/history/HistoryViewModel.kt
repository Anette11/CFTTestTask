package com.example.cfttesttask.ui.components.history

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
class HistoryViewModel @Inject constructor(
    private val repository: CardRepository,
    private val resourcesProvider: ResourcesProvider,
    private val coroutineDispatchersProvider: CoroutineDispatchersProvider
) : ViewModel() {

    private val _showDialogClearHistory: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showDialogClearHistory: StateFlow<Boolean> = _showDialogClearHistory

    fun onShowDialogClearHistory() {
        _showDialogClearHistory.value = !_showDialogClearHistory.value
    }

    private val _allCardInfos: MutableStateFlow<List<CardInfoDbo>> = MutableStateFlow(emptyList())
    val allCardInfos: StateFlow<List<CardInfoDbo>> = _allCardInfos

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error: MutableSharedFlow<String> = MutableSharedFlow()
    val error: SharedFlow<String> = _error

    private fun getAllCardInfos() = viewModelScope.launch(
        createCoroutineExceptionHandler(
            onError = {
                viewModelScope.launch(coroutineDispatchersProvider.io) {
                    _isLoading.emit(false)
                    _error.emit(resourcesProvider.getString(R.string.generic_error))
                }
            }
        )
    ) {
        repository.getAllCardInfos().collect { resource: NetworkResource<List<CardInfoDbo>> ->
            when (resource) {
                is NetworkResource.Loading -> _isLoading.emit(true)
                is NetworkResource.Success -> {
                    _isLoading.emit(false)
                    val allCardInfos = resource.data
                    _allCardInfos.emit(allCardInfos ?: emptyList())
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

    fun createCardInfo(
        cardInfoDbo: CardInfoDbo
    ): List<Item> = repository.createCardInfo(cardInfoDbo = cardInfoDbo)

    fun clearAllCardInfos() = viewModelScope.launch(
        createCoroutineExceptionHandler(
            onError = {
                viewModelScope.launch(coroutineDispatchersProvider.io) {
                    _isLoading.emit(false)
                    _error.emit(resourcesProvider.getString(R.string.generic_error))
                }
            }
        )
    ) {
        onShowDialogClearHistory()
        repository.clearAllCardInfos()
        getAllCardInfos()
    }

    init {
        getAllCardInfos()
    }
}