package com.challenge.popularmovieapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.challenge.popularmovieapp.data.local.dao.MovieDao
import com.challenge.popularmovieapp.data.local.dao.RemoteKeyDao
import com.challenge.popularmovieapp.data.local.entity.MovieEntity
import com.challenge.popularmovieapp.data.local.entity.RemoteKeyEntity

@Database(
    entities = [MovieEntity::class, RemoteKeyEntity::class],
    version = 1
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}
