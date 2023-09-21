package com.tonimurr.marvel.data.db.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insert(entity: T)

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insert(entities: List<T>)

    @Update
    suspend fun update(entity: T)

    @Update
    suspend fun update(entities: List<T>)

    @Delete
    suspend fun delete(entity: T)

    @Delete
    suspend fun delete(entities: List<T>)

}