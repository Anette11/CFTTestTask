package com.example.cfttesttask.ui.components.history

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.example.cfttesttask.R

@Composable
fun HistoryAlertDialog(
    onConfirmClick: () -> Unit,
    onDismissClick: () -> Unit
) = AlertDialog(onDismissRequest = { },
    title = {
        Text(
            text = stringResource(id = R.string.history_alert_dialog_title),
            color = Color.Black,
            fontSize = dimensionResource(id = R.dimen._20sp).value.sp,
            fontWeight = FontWeight.Bold
        )
    },
    text = {
        Text(
            text = stringResource(id = R.string.history_alert_dialog_text),
            fontSize = dimensionResource(id = R.dimen._18sp).value.sp
        )
    },
    confirmButton = {
        HistoryButton(
            onClick = onConfirmClick,
            buttonText = stringResource(id = R.string.history_alert_dialog_confirm_button),
            paddingEnd = dimensionResource(id = R.dimen._16dp)
        )
    },
    dismissButton = {
        HistoryButton(
            onClick = onDismissClick,
            buttonText = stringResource(id = R.string.history_alert_dialog_dismiss_button),
            paddingEnd = dimensionResource(id = R.dimen._0dp)
        )
    }
)

@Composable
private fun HistoryButton(
    onClick: () -> Unit,
    buttonText: String,
    paddingEnd: Dp
) = OutlinedButton(
    onClick = onClick,
    border = BorderStroke(
        width = dimensionResource(id = R.dimen._0dp),
        color = colorResource(id = R.color.green)
    ),
    colors = ButtonDefaults.outlinedButtonColors(backgroundColor = colorResource(id = R.color.green)),
    modifier = Modifier.padding(
        bottom = dimensionResource(id = R.dimen._16dp),
        end = paddingEnd
    )
) {
    Text(
        text = buttonText,
        fontSize = dimensionResource(id = R.dimen._18sp).value.sp,
        color = Color.White
    )
}