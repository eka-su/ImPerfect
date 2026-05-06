package com.example.imperfect.core.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

@Entity(
    tableName = "app_user",
    indices = [Index(value = ["email"], unique = true)]
)
data class AppUserEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,

    val email: String,

    @ColumnInfo(name = "password_hash")
    val passwordHash: String,

    @ColumnInfo(name = "skin_type")
    val skinType: String?,

    @ColumnInfo(name = "created_at")
    val createdAt: String
)