package com.example.cfttesttask.ui.components.common

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.example.cfttesttask.R

@Composable
fun SpaceItem() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._8dp)))
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = Color.LightGray.copy(alpha = 0.2f)
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._8dp)))
    }
}
