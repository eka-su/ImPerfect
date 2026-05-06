package com.example.imperfect.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
@Entity(
    tableName = "diary_photo",
    foreignKeys = [
        ForeignKey(
            entity = SkinDiaryDayEntity::class,
            parentColumns = ["id"],
            childColumns = ["diary_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PhotoViewTypeEntity::class,
            parentColumns = ["id"],
            childColumns = ["view_type_id"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [
        Index("diary_id"),
        Index("view_type_id")
    ]
)
data class DiaryPhotoEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "diary_id")
    val diaryId: Int,

    @ColumnInfo(name = "file_path")
    val filePath: String,

    @ColumnInfo(name = "view_type_id")
    val viewTypeId: Int,

    @ColumnInfo(name = "created_at")
    val createdAt: String
)