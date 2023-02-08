package com.example.cfttesttask.data.local.dbo

import androidx.room.ColumnInfo

data class CountryDbo(
    val alpha2: String?,
    val currency: String?,
    val emoji: String?,
    val latitude: Int?,
    val longitude: Int?,

    @ColumnInfo(name = "countryName")
    val name: String?,
    val numeric: String?
)