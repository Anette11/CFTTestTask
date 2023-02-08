package com.example.cfttesttask.util

sealed class Luhn {

    object Yes : Luhn()
    object No : Luhn()
}