package com.example.cfttesttask.util

sealed class Prepaid {

    object Yes : Prepaid()
    object No : Prepaid()
}