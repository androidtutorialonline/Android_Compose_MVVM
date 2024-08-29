package com.androidapps.composeMVVM.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) for interacting with user data in the database.
 *
 * This interface defines methods for querying and inserting user data in the database.
 *
 * @Dao annotation indicates that this interface is a Data Access Object for Room.
 */
@Dao
interface ItemDao {

    /**
     * Retrieves all user items from the database.
     *
     * @return A [Flow] that emits a list of [UserEntity] objects. The data is updated in real-time as
     *         changes occur in the database.
     */
    @Query("SELECT * FROM items")
    fun getAllItems(): Flow<List<UserEntity>>

    /**
     * Inserts a list of user items into the database. If any item already exists, it will be replaced.
     *
     * @param items A list of [UserEntity] objects to be inserted into the database.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItems(items: List<UserEntity>)
}

