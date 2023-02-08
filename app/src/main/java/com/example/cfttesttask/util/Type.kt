package com.example.cfttesttask.util

sealed class Type {

    object Debit : Type()
    object Credit : Type()
}