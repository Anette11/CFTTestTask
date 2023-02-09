package com.example.cfttesttask.ui.components.home

import android.widget.Toast
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cfttesttask.ui.components.common.*
import com.example.cfttesttask.util.Item

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val isLoading by viewModel.isLoading.collectAsState()
    val cardInfo by viewModel.cardInfo.collectAsState()
    val value by viewModel.value.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.error.collect { error: String ->
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            HomeAppBar()
        }
    ) { paddingValues: PaddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues = paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
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
                LazyColumn {
                    items(cardInfo) { item: Item ->
                        when (item) {
                            is Item.SchemeNetwork -> SchemeNetworkItem(item = item)
                            is Item.Brand -> BrandItem(item = item)
                            is Item.CardNumber -> CardNumberItem(item = item)
                            is Item.Type -> TypeItem(item = item)
                            is Item.Prepaid -> PrepaidItem(item = item)
                            is Item.Country -> CountryItem(item = item)
                            is Item.Bank -> BankItem(item = item)
                            Item.Space -> SpaceItem()
                        }
                    }
                }
            }
            if (isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}