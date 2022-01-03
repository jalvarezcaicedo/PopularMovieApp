package com.challenge.popularmovieapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.challenge.popularmovieapp.data.local.entity.RemoteKeyEntity

@Dao
interface RemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: RemoteKeyEntity)

    @Query("SELECT * FROM remote_keys WHERE title = :title")
    suspend fun remoteKeyByTitle(title: String): RemoteKeyEntity

    @Query("DELETE FROM remote_keys WHERE title = :title")
    suspend fun deleteByTitle(title: String)

}