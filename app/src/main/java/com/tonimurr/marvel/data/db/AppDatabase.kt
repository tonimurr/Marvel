package com.tonimurr.marvel.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tonimurr.marvel.data.db.daos.MarvelCharacterDao
import com.tonimurr.marvel.data.model.MarvelCharacterDTO

@Database(entities = [MarvelCharacterDTO::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun marvelCharacterDao(): MarvelCharacterDao

}