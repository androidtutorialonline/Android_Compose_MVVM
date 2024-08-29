package com.androidapps.composeMVVM.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * The Room database class for the application.
 *
 * This class provides access to the database and contains the database version,
 * along with the DAOs that define methods for accessing and modifying data in the database.
 *
 * @property itemDao DAO for interacting with user data in the database.
 *
 * @Database annotation specifies the database entities and version.
 * - `entities` parameter lists all the entities that belong to this database.
 * - `version` specifies the version of the database schema. Increment this number when
 *   schema changes are made.
 */
@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Provides access to the `ItemDao` for performing database operations.
     *
     * @return An instance of `ItemDao` for accessing user data.
     */
    abstract fun itemDao(): ItemDao
}

