package com.example.cfttesttask.ui.components.history

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cfttesttask.R

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val showDialogClearHistory by viewModel.showDialogClearHistory.collectAsState()
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
            if (showDialogClearHistory) {
                HistoryAlertDialog(
                    onConfirmClick = {},
                    onDismissClick = viewModel::onShowDialogClearHistory
                )
            }
        }
    }
}