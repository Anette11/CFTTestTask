package com.example.cfttesttask.ui.components.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.cfttesttask.R

@Composable
fun SearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onClear: () -> Unit,
    onSearch: () -> Unit,
    isClearEnable: Boolean,
    isSearchEnable: Boolean
) {
    val focusManager = LocalFocusManager.current
    val textSelectionColors = TextSelectionColors(
        handleColor = Color.LightGray,
        backgroundColor = Color.LightGray
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen._8dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CompositionLocalProvider(LocalTextSelectionColors provides textSelectionColors) {
            OutlinedTextField(
                value = value,
                onValueChange = { newValue: String -> onValueChange(newValue) },
                modifier = Modifier.weight(1f),
                singleLine = true,
                label = { Text(text = stringResource(id = R.string.search_text_field_label)) },
                placeholder = { Text(text = stringResource(id = R.string.search_text_field_placeholder)) },
                trailingIcon = {
                    IconButton(
                        enabled = isClearEnable,
                        onClick = onClear
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(id = R.string.icon_close_content_description)
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
            )
        }
        IconButton(
            enabled = isSearchEnable,
            onClick = {
                onSearch()
                focusManager.clearFocus()
            }
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(id = R.string.icon_search_content_description)
            )
        }
    }
}