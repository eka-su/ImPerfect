package com.example.imperfect.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "care_routine_items",
    foreignKeys = [

        ForeignKey(
            entity = SkinDiaryDayEntity::class,
            parentColumns = ["id"],
            childColumns = ["diaryId"],
            onDelete = ForeignKey.CASCADE
        ),

        ForeignKey(
            entity = SkincareProductEntity::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("diaryId"),
        Index("productId")
    ]
)
data class CareRoutineItemEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val diaryId: Int,

    val productId: Int,

    val isUsed: Boolean = false
)