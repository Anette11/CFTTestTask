package com.example.cfttesttask.util

import androidx.annotation.StringRes

interface ResourcesProvider {

    fun getString(@StringRes stringRes: Int): String
}