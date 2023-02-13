package com.example.cfttesttask.ui.components.history

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(

) : ViewModel() {

    private val _showDialogClearHistory: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showDialogClearHistory: StateFlow<Boolean> = _showDialogClearHistory

    fun onShowDialogClearHistory() {
        _showDialogClearHistory.value = !_showDialogClearHistory.value
    }
}