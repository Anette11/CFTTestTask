package com.example.cfttesttask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cfttesttask.data.local.dbo.CardInfoDbo

@Database(
    entities = [CardInfoDbo::class],
    version = CardDatabase.version
)
@TypeConverters(value = [CardConverters::class])
abstract class CardDatabase : RoomDatabase() {

    companion object {
        const val version = 2
        const val name = "card_db"
    }

    abstract val dao: CardDao
}