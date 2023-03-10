package com.example.cfttesttask.ui.components.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.cfttesttask.R

@Composable
fun HomeAppBar(
    onHistoryClick: () -> Unit
) = TopAppBar(
    modifier = Modifier.fillMaxWidth(),
    title = { Text(text = stringResource(id = R.string.home_app_bar_title)) },
    backgroundColor = colorResource(id = R.color.light_green),
    contentColor = colorResource(id = R.color.white),
    actions = {
        IconButton(onClick = onHistoryClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_history),
                contentDescription = stringResource(id = R.string.icon_history_content_description),
                tint = Color.White
            )
        }
    }
)