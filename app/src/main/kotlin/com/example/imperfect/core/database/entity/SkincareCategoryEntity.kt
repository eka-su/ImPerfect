package com.example.imperfect.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "skincare_categories")
data class SkincareCategoryEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
    val code: String,
)