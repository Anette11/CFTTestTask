package com.example.cfttesttask.ui.components.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cfttesttask.R
import com.example.cfttesttask.ui.components.common.CardInfo
import com.example.cfttesttask.ui.components.common.SearchTextField

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onPhoneClick: (String) -> Unit,
    onUrlClick: (String) -> Unit,
    onCoordinatesClick: (Double, Double) -> Unit,
    onHistoryClick: () -> Unit,
    onError: (String) -> Unit
) {
    val isLoading by viewModel.isLoading.collectAsState()
    val cardInfo by viewModel.cardInfo.collectAsState()
    val value by viewModel.value.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.error.collect { message: String -> onError(message) }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { HomeAppBar(onHistoryClick = onHistoryClick) },
        backgroundColor = colorResource(id = R.color.gray)
    ) { paddingValues: PaddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues = paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                SearchTextField(
                    value = value,
                    onValueChange = { newValue: String ->
                        viewModel.onValueChange(newValue = newValue)
                    },
                    onClear = viewModel::onClear,
                    onSearch = viewModel::getCardInfo,
                    isClearEnable = viewModel.isEnable(),
                    isSearchEnable = viewModel.isEnable()
                )
                if (cardInfo.isNotEmpty()) CardInfo(
                    cardInfo = cardInfo,
                    onPhoneClick = { phone: String -> onPhoneClick(phone) },
                    onUrlClick = { url: String -> onUrlClick(url) },
                    onCoordinatesClick = { latitude: Double, longitude: Double ->
                        onCoordinatesClick(latitude, longitude)
                    }
                )
            }
            if (isLoading) CircularProgressIndicator(color = colorResource(id = R.color.green))
        }
    }
}