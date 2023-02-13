package com.example.cfttesttask.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun createDateString(
    date: Date?,
    defaultDate: String
): String {
    if (date == null) return defaultDate
    return try {
        val simpleDateFormat = SimpleDateFormat("EEE, dd MMM yyyy, HH:mm:ss", Locale.getDefault())
        simpleDateFormat.format(date)
    } catch (e: ParseException) {
        defaultDate
    }
}