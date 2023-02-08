package com.example.cfttesttask.ui.components.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.cfttesttask.R

@Composable
fun HomeAppBar() =
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        title = {
            Text(text = stringResource(id = R.string.home_app_bar_title))
        }
    )