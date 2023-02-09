package com.example.cfttesttask.ui.components.home

import androidx.lifecycle.ViewModel
import com.example.cfttesttask.repository.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: CardRepository
) : ViewModel() {

}