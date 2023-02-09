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
import com.example.cfttesttask.util.Type

@Composable
fun TypeItem(
    item: Item.Type
) = Column(
    modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Text(
        text = stringResource(id = R.string.item_title_type),
        color = colorResource(id = R.color.medium_green),
        style = TextStyle(fontSize = dimensionResource(id = R.dimen._20sp).value.sp)
    )
    when (item.type) {
        is Type.Debit -> Text(
            buildAnnotatedString {
                append(stringResource(id = R.string.item_subtitle_type_debit))
                withStyle(style = SpanStyle(color = Color.LightGray)) {
                    append(" / ${stringResource(id = R.string.item_subtitle_type_credit)}")
                }
            },
            style = TextStyle(fontSize = dimensionResource(id = R.dimen._18sp).value.sp)
        )
        is Type.Credit -> Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.LightGray)) {
                    append("${stringResource(id = R.string.item_subtitle_type_debit)} / ")
                }
                append(stringResource(id = R.string.item_subtitle_type_credit))
            },
            style = TextStyle(fontSize = dimensionResource(id = R.dimen._18sp).value.sp)
        )
        is Type.Unknown -> Text(
            text = "${stringResource(id = R.string.item_subtitle_type_debit)} /" +
                    " ${stringResource(id = R.string.item_subtitle_type_credit)}",
            color = Color.LightGray,
            fontSize = dimensionResource(id = R.dimen._18sp).value.sp
        )
    }
}