package com.tonimurr.marvel.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tonimurr.marvel.data.db.daos.ComicsDao
import com.tonimurr.marvel.data.db.daos.MarvelCharacterDao
import com.tonimurr.marvel.data.model.ComicsDTO
import com.tonimurr.marvel.data.model.MarvelCharacterDTO

@Database(entities = [MarvelCharacterDTO::class, ComicsDTO::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun marvelCharacterDao(): MarvelCharacterDao
    abstract fun comicsDao(): ComicsDao

}