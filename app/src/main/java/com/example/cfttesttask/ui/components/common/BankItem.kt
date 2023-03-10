package com.example.cfttesttask.ui.components.common

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.cfttesttask.R
import com.example.cfttesttask.util.Item

@Composable
fun BankItem(
    item: Item.Bank,
    onPhoneClick: (String) -> Unit,
    onUrlClick: (String) -> Unit,
    isOnPhoneClickEnable: Boolean,
    isUrlClickable: Boolean
) = Column(
    modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Text(
        text = stringResource(id = R.string.item_title_bank),
        color = colorResource(id = R.color.green),
        style = TextStyle(fontSize = dimensionResource(id = R.dimen._20sp).value.sp)
    )
    Text(
        text = "${item.name}, ${item.city}",
        style = TextStyle(fontSize = dimensionResource(id = R.dimen._18sp).value.sp)
    )
    if (isUrlClickable) {
        Text(
            text = item.url,
            color = Color.Blue,
            style = TextStyle(fontSize = dimensionResource(id = R.dimen._18sp).value.sp),
            modifier = Modifier.clickable { onUrlClick(item.url) }
        )
    } else {
        Text(
            text = item.url,
            color = Color.Blue,
            style = TextStyle(fontSize = dimensionResource(id = R.dimen._18sp).value.sp)
        )
    }
    if (isOnPhoneClickEnable) {
        Text(
            text = item.phone,
            style = TextStyle(fontSize = dimensionResource(id = R.dimen._18sp).value.sp),
            modifier = Modifier.clickable { onPhoneClick(item.phone) }
        )
    } else {
        Text(
            text = item.phone,
            style = TextStyle(fontSize = dimensionResource(id = R.dimen._18sp).value.sp)
        )
    }
}