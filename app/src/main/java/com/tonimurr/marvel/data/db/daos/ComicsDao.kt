package com.tonimurr.marvel.data.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.tonimurr.marvel.data.model.ComicsDTO

@Dao
abstract class ComicsDao : BaseDao<ComicsDTO> {

    @Query("SELECT * FROM Comics WHERE characterId = :characterId ORDER BY title")
    abstract suspend fun getComicsByCharacterId(characterId: Long) : List<ComicsDTO>

}