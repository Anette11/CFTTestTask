package com.example.cfttesttask.data.local

data class CardInfoDbo(
    val bank: BankDbo?,
    val brand: String?,
    val country: CountryDbo?,
    val number: NumberDbo?,
    val prepaid: Boolean?,
    val scheme: String?,
    val type: String?
)