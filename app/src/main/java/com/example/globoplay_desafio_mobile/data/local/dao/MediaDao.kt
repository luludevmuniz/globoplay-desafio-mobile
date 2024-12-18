package com.example.globoplay_desafio_mobile.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.globoplay_desafio_mobile.data.local.entity.MediaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MediaDao {
    @Query("SELECT * FROM MediaEntity")
    fun getAllMedia(): Flow<List<MediaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedia(media: MediaEntity)

    @Query("DELETE FROM MediaEntity WHERE id = :id")
    suspend fun deleteMedia(id: Int)

    @Query("SELECT EXISTS(SELECT * FROM MediaEntity WHERE id = :id)")
    fun isMediaInMyList(id: Int): Flow<Boolean>
}