package com.example.cfttesttask.data.local.dbo

import androidx.room.ColumnInfo

data class BankDbo(
    val city: String?,

    @ColumnInfo(name = "bankName")
    val name: String?,

    val phone: String?,
    val url: String?
)