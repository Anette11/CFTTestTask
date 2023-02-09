package com.example.cfttesttask.ui.components.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.cfttesttask.R
import com.example.cfttesttask.util.Item

@Composable
fun CountryItem(
    item: Item.Country
) = Column(
    modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Text(
        text = stringResource(id = R.string.item_title_country),
        color = colorResource(id = R.color.medium_green),
        style = TextStyle(fontSize = dimensionResource(id = R.dimen._20sp).value.sp)
    )
    Text(
        text = "${item.emoji} ${item.name}",
        style = TextStyle(fontSize = dimensionResource(id = R.dimen._18sp).value.sp)
    )
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.LightGray)) {
                append("(${stringResource(id = R.string.item_subtitle_country_latitude)}: ")
            }
            append("${item.latitude}")
            withStyle(style = SpanStyle(color = Color.LightGray)) {
                append(", ${stringResource(id = R.string.item_subtitle_country_longitude)}: ")
            }
            append("${item.longitude}")
            withStyle(style = SpanStyle(color = Color.LightGray)) {
                append(")")
            }
        },
        style = TextStyle(fontSize = dimensionResource(id = R.dimen._18sp).value.sp)
    )
}