package com.example.cfttesttask.data.mappers

import com.example.cfttesttask.data.local.BankDbo
import com.example.cfttesttask.data.local.CardInfoDbo
import com.example.cfttesttask.data.local.CountryDbo
import com.example.cfttesttask.data.local.NumberDbo
import com.example.cfttesttask.data.remote.dto.BankDto
import com.example.cfttesttask.data.remote.dto.CardInfoDto
import com.example.cfttesttask.data.remote.dto.CountryDto
import com.example.cfttesttask.data.remote.dto.NumberDto

fun BankDto.toBankDbo() = BankDbo(
    city = this.city,
    name = this.name,
    phone = this.phone,
    url = this.url
)

fun CardInfoDto.toCardInfoDbo() = CardInfoDbo(
    bank = this.bank?.toBankDbo(),
    brand = this.brand,
    country = this.country?.toCountryDbo(),
    number = this.number?.toNumberDbo(),
    prepaid = this.prepaid,
    scheme = this.scheme,
    type = this.type
)

fun CountryDto.toCountryDbo() = CountryDbo(
    alpha2 = this.alpha2,
    currency = this.currency,
    emoji = this.emoji,
    latitude = this.latitude,
    longitude = this.longitude,
    name = this.name,
    numeric = this.numeric
)

fun NumberDto.toNumberDbo() = NumberDbo(
    length = this.length,
    luhn = this.luhn
)