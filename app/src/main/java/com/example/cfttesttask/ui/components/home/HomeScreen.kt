package com.example.cfttesttask.ui.components.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen() =
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            HomeAppBar()
        }
    ) { paddingValues: PaddingValues ->
        Column(modifier = Modifier.padding(paddingValues = paddingValues)) {

        }
    }