package com.example.imperfect.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "diary_feeling_notes",
    indices = [
        Index(value = ["diary_id", "screen_id"], unique = true)
    ],
    foreignKeys = [
        ForeignKey(
            entity = SkinDiaryDayEntity::class,
            parentColumns = ["id"],
            childColumns = ["diary_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = FeelingScreenEntity::class,
            parentColumns = ["id"],
            childColumns = ["screen_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DiaryFeelingNoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "diary_id")
    val diaryId: Int,

    @ColumnInfo(name = "screen_id")
    val screenId: Int,

    val notes: String
)