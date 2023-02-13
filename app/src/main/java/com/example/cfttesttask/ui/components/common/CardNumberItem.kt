package com.example.cfttesttask.ui.components.common

import androidx.compose.foundation.layout.*
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
import com.example.cfttesttask.util.Luhn

@Composable
fun CardNumberItem(
    item: Item.CardNumber
) = Column(
    modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Text(
        text = stringResource(id = R.string.item_title_card_number),
        color = colorResource(id = R.color.green),
        style = TextStyle(fontSize = dimensionResource(id = R.dimen._20sp).value.sp)
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.item_subtitle_card_number_length),
                color = Color.Gray,
                style = TextStyle(fontSize = dimensionResource(id = R.dimen._18sp).value.sp)
            )
            Text(
                text = item.length,
                style = TextStyle(fontSize = dimensionResource(id = R.dimen._18sp).value.sp)
            )
        }
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen._16dp)))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.item_subtitle_card_number_luhn),
                color = Color.Gray,
                style = TextStyle(fontSize = dimensionResource(id = R.dimen._18sp).value.sp)
            )
            when (item.luhn) {
                is Luhn.Yes -> Text(
                    buildAnnotatedString {
                        append(stringResource(id = R.string.yes))
                        withStyle(style = SpanStyle(color = Color.LightGray)) {
                            append(" / ${stringResource(id = R.string.no)}")
                        }
                    },
                    style = TextStyle(fontSize = dimensionResource(id = R.dimen._18sp).value.sp)
                )
                is Luhn.No -> Text(
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.LightGray)) {
                            append("${stringResource(id = R.string.yes)} / ")
                        }
                        append(stringResource(id = R.string.no))
                    },
                    style = TextStyle(fontSize = dimensionResource(id = R.dimen._18sp).value.sp)
                )
                is Luhn.Unknown -> Text(
                    text = "${stringResource(id = R.string.yes)} /" +
                            " ${stringResource(id = R.string.no)}",
                    color = Color.LightGray,
                    fontSize = dimensionResource(id = R.dimen._18sp).value.sp
                )
            }
        }
    }
}