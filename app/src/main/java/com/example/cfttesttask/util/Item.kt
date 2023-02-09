package com.example.cfttesttask.util

sealed class Item {
    data class SchemeNetwork(
        val schemeNetwork: String
    ) : Item()

    data class Brand(
        val brand: String
    ) : Item()

    data class CardNumber(
        val length: String,
        val luhn: Luhn
    ) : Item()

    data class Type(
        val type: com.example.cfttesttask.util.Type
    ) : Item()

    data class Prepaid(
        val prepaid: com.example.cfttesttask.util.Prepaid
    ) : Item()

    data class Country(
        val emoji: String,
        val name: String,
        val latitude: String,
        val longitude: String
    ) : Item()

    data class Bank(
        val name: String,
        val city: String,
        val url: String,
        val phone: String
    ) : Item()

    object Space : Item()
}

sealed class Luhn {
    object Yes : Luhn()
    object No : Luhn()
    object Unknown : Luhn()
}

sealed class Prepaid {
    object Yes : Prepaid()
    object No : Prepaid()
    object Unknown : Prepaid()
}

sealed class Type {
    object Debit : Type()
    object Credit : Type()
    object Unknown : Type()
}