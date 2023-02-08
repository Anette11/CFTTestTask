package com.example.cfttesttask.util

sealed class Item {

    data class SchemeNetwork(
        val schemeNetwork: String
    ) : Item()

    data class Brand(
        val brand: String
    ) : Item()

    data class CardNumber(
        val length: Int,
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
        val latitude: Double,
        val longitude: Double
    ) : Item()

    data class Bank(
        val name: String,
        val city: String,
        val url: String,
        val phone: String
    ) : Item()

    object Space : Item()
}