package com.example.cfttesttask.ui.components.history

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
fun HistoryAppBar(
    onBackClick: () -> Unit,
    onClearClick: () -> Unit
) = TopAppBar(
    modifier = Modifier.fillMaxWidth(),
    title = { Text(text = stringResource(id = R.string.history_app_bar_title)) },
    backgroundColor = colorResource(id = R.color.light_green),
    contentColor = colorResource(id = R.color.white),
    navigationIcon = {
        IconButton(onClick = onBackClick) {
            Icon(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = stringResource(id = R.string.icon_back_content_description)
            )
        }
    },
    actions = {
        IconButton(onClick = onClearClick) {
            Icon(
                painter = painterResource(R.drawable.ic_clear),
                contentDescription = stringResource(id = R.string.icon_clear_content_description),
                tint = Color.White
            )
        }
    }
)