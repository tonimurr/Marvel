package com.tonimurr.marvel.data.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.tonimurr.marvel.data.model.MarvelCharacterDTO

@Dao
abstract class MarvelCharacterDao : BaseDao<MarvelCharacterDTO> {

    @Query("SELECT * FROM MarvelCharacter ORDER BY name")
    abstract suspend fun getAll(): List<MarvelCharacterDTO>

}