package com.example.imperfect.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "care_routine_time_links",
    foreignKeys = [

        ForeignKey(
            entity = CareRoutineItemEntity::class,
            parentColumns = ["id"],
            childColumns = ["careItemId"],
            onDelete = ForeignKey.CASCADE
        ),

        ForeignKey(
            entity = CareTimeSlotEntity::class,
            parentColumns = ["id"],
            childColumns = ["timeId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("careItemId"),
        Index("timeId")
    ]
)
data class CareRoutineTimeLinkEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val careItemId: Int,

    val timeId: Int
)