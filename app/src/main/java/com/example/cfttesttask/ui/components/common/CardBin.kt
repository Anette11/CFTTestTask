package com.example.cfttesttask.ui.components.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.cfttesttask.R

@Composable
fun CardBin(
    bin: String
) = Text(
    modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = dimensionResource(id = R.dimen._8dp)),
    text = "BIN: $bin",
    style = TextStyle(fontSize = dimensionResource(id = R.dimen._18sp).value.sp),
    color = Color.Black
)