package com.example.cfttesttask.ui.components.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.cfttesttask.ui.components.common.CardBin
import com.example.cfttesttask.ui.components.common.CardDate
import com.example.cfttesttask.ui.components.common.CardInfo

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onPhoneClick: (String) -> Unit,
    onUrlClick: (String) -> Unit,
    onCoordinatesClick: (Double, Double) -> Unit,
    onError: (String) -> Unit
) {
    val showDialogClearHistory by viewModel.showDialogClearHistory.collectAsState()
    val allCardInfos by viewModel.allCardInfos.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.error.collect { message: String -> onError(message) }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            HistoryAppBar(
                onBackClick = onBackClick,
                onClearClick = viewModel::onShowDialogClearHistory
            )
        },
        backgroundColor = colorResource(id = R.color.gray)
    ) { paddingValues: PaddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(allCardInfos) { cardInfoDbo ->
                    CardDate(date = viewModel.createDateString(date = cardInfoDbo.date))
                    CardBin(bin = cardInfoDbo.bin)
                    CardInfo(
                        cardInfo = viewModel.createCardInfo(cardInfoDbo = cardInfoDbo),
                        onPhoneClick = { phone: String -> onPhoneClick(phone) },
                        onUrlClick = { url: String -> onUrlClick(url) },
                        onCoordinatesClick = { latitude: Double, longitude: Double ->
                            onCoordinatesClick(latitude, longitude)
                        }
                    )
                }
            }
            if (isLoading) CircularProgressIndicator(color = colorResource(id = R.color.green))
            if (showDialogClearHistory) {
                HistoryAlertDialog(
                    onConfirmClick = viewModel::clearAllCardInfos,
                    onDismissClick = viewModel::onShowDialogClearHistory
                )
            }
        }
    }
}