package com.example.cfttesttask.data.local

import androidx.room.TypeConverter
import java.util.*

class CardConverters {

    @TypeConverter
    fun dateFromLong(dateLong: Long?): Date? =
        dateLong?.let { Date(dateLong) }

    @TypeConverter
    fun longFromDate(date: Date?): Long? =
        date?.let { date.time }
}