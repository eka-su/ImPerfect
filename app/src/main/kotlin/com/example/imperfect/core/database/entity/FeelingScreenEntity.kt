package com.example.imperfect.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feeling_screens")
data class FeelingScreenEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val code: String, // SKIN / HEALTH + EMOTION
    val name: String
)