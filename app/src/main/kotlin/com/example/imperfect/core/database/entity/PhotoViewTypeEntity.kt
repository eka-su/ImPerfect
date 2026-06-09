package com.example.imperfect.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo_view_type")
data class PhotoViewTypeEntity(

    @PrimaryKey
    val id: Int,

    val code: String,
    val name: String
)