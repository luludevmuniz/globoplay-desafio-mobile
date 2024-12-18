package com.example.globoplay_desafio_mobile.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.globoplay_desafio_mobile.data.local.dao.MediaDao
import com.example.globoplay_desafio_mobile.data.local.entity.MediaEntity

@Database(
    entities = [MediaEntity::class],
    version = 1,
    exportSchema = false
)
abstract class Database: RoomDatabase() {
    abstract fun mediaDao(): MediaDao
}