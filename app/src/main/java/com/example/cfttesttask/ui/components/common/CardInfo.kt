package com.example.cfttesttask.ui.components.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.cfttesttask.R
import com.example.cfttesttask.util.Item

@Composable
fun CardInfo(
    cardInfo: List<Item>,
    onPhoneClick: (String) -> Unit,
    onUrlClick: (String) -> Unit,
    onCoordinatesClick: (Double, Double) -> Unit
) = Card(
    modifier = Modifier
        .padding(dimensionResource(id = R.dimen._8dp))
        .fillMaxWidth(),
    elevation = dimensionResource(id = R.dimen._0dp),
    border = BorderStroke(
        width = dimensionResource(id = R.dimen._1dp),
        color = Color.Gray
    )
) {
    LazyColumn(modifier = Modifier.padding(dimensionResource(id = R.dimen._8dp))) {
        items(cardInfo) { item: Item ->
            when (item) {
                is Item.SchemeNetwork -> SchemeNetworkItem(item = item)
                is Item.Brand -> BrandItem(item = item)
                is Item.CardNumber -> CardNumberItem(item = item)
                is Item.Type -> TypeItem(item = item)
                is Item.Prepaid -> PrepaidItem(item = item)
                is Item.Country -> CountryItem(
                    item = item,
                    onCoordinatesClick = { latitude: Double, longitude: Double ->
                        onCoordinatesClick(latitude, longitude)
                    },
                    areCoordinatesClickable = item.latitude != stringResource(id = R.string.not_applicable)
                            && item.longitude != stringResource(id = R.string.not_applicable)
                )
                is Item.Bank -> BankItem(
                    item = item,
                    onPhoneClick = { phone: String -> onPhoneClick(phone) },
                    onUrlClick = { url: String -> onUrlClick(url) },
                    isOnPhoneClickEnable = item.phone.all { char -> !char.isLetter() },
                    isUrlClickable = item.url != stringResource(id = R.string.not_applicable)
                )
                Item.Space -> SpaceItem()
            }
        }
    }
}