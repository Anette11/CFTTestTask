package com.example.cfttesttask.data.local.dbo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_info")
data class CardInfoDbo(
    @PrimaryKey(autoGenerate = false)
    val bin: String,

    @Embedded
    val bank: BankDbo?,

    val brand: String?,

    @Embedded
    val country: CountryDbo?,

    @Embedded
    val number: NumberDbo?,

    val prepaid: Boolean?,
    val scheme: String?,
    val type: String?
)