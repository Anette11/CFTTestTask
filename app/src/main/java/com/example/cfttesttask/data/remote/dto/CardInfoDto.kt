package com.example.cfttesttask.data.remote.dto

data class CardInfoDto(
    val bank: BankDto?,
    val brand: String?,
    val country: CountryDto?,
    val number: NumberDto?,
    val prepaid: Boolean?,
    val scheme: String?,
    val type: String?
)