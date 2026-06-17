package com.example.imperfect.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "care_time_slots")
data class CareTimeSlotEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val code: String,
    val name: String,
)