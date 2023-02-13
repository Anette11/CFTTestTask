package com.example.cfttesttask.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import com.example.cfttesttask.R
import com.example.cfttesttask.data.local.dbo.CardInfoDbo

fun AnnotatedString.cardFilter(): TransformedText {
    val trimmed = if (this.text.length >= 16) this.text.substring(0..15) else this.text
    var out = ""
    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i % 4 == 3 && i != 15) out += " "
    }
    val creditCardOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 3) return offset
            if (offset <= 7) return offset + 1
            if (offset <= 11) return offset + 2
            if (offset <= 16) return offset + 3
            return 19
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 4) return offset
            if (offset <= 9) return offset - 1
            if (offset <= 14) return offset - 2
            if (offset <= 19) return offset - 3
            return 16
        }
    }
    return TransformedText(
        text = AnnotatedString(out),
        offsetMapping = creditCardOffsetTranslator
    )
}