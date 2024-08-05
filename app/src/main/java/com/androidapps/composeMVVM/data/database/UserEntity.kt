package com.androidapps.composeMVVM.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class UserEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val description: String
)

