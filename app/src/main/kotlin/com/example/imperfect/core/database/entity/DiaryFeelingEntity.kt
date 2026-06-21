package com.example.imperfect.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "diary_feelings",
    foreignKeys = [
        ForeignKey(
            entity = SkinDiaryDayEntity::class,
            parentColumns = ["id"],
            childColumns = ["diary_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = FeelingOptionEntity::class,
            parentColumns = ["id"],
            childColumns = ["feeling_option_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("diary_id"),
        Index("feeling_option_id")
    ]
)
data class DiaryFeelingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "diary_id")
    val diaryId: Int,

    @ColumnInfo(name = "feeling_option_id")
    val feelingOptionId: Int
)