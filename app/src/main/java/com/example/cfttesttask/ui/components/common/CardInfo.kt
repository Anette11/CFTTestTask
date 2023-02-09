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
import com.example.cfttesttask.R
import com.example.cfttesttask.util.Item

@Composable
fun CardInfo(
    cardInfo: List<Item>
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
                is Item.Country -> CountryItem(item = item)
                is Item.Bank -> BankItem(item = item)
                Item.Space -> SpaceItem()
            }
        }
    }
}