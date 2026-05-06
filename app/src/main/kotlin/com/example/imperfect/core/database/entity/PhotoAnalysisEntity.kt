package com.example.imperfect.core.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.ColumnInfo
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "photo_analysis",
    foreignKeys = [
        ForeignKey(
            entity = DiaryPhotoEntity::class,
            parentColumns = ["id"],
            childColumns = ["photo_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PhotoAnalysisStatusEntity::class,
            parentColumns = ["id"],
            childColumns = ["status_id"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [
        Index("photo_id"),
        Index("status_id")
    ]
)
data class PhotoAnalysisEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "photo_id")
    val photoId: Int,

    @ColumnInfo(name = "status_id")
    val statusId: Int,

    @ColumnInfo(name = "created_at")
    val createdAt: String
)