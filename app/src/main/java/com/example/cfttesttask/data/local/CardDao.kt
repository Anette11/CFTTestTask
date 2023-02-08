package com.example.cfttesttask.data.local

import androidx.room.Dao
import androidx.room.Query
import com.example.cfttesttask.data.local.dbo.CardInfoDbo

@Dao
interface CardDao {

    @Query("SELECT * FROM card_info")
    fun getCardInfo(): CardInfoDbo
}