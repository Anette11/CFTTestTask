package com.example.cfttesttask.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cfttesttask.data.local.dbo.CardInfoDbo

@Dao
interface CardDao {

    @Query("SELECT * FROM card_info WHERE bin = :bin")
    fun getCardInfo(bin: String): CardInfoDbo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCardInfo(cardInfoDbo: CardInfoDbo)
}