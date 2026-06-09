package com.example.imperfect.core.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.ColumnInfo
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "multi_image_analysis_photo",
    foreignKeys = [
        ForeignKey(
            entity = MultiImageAnalysisEntity::class,
            parentColumns = ["id"],
            childColumns = ["multi_analysis_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PhotoAnalysisEntity::class,
            parentColumns = ["id"],
            childColumns = ["photo_analysis_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("multi_analysis_id"),
        Index("photo_analysis_id")
    ]
)

data class MultiImageAnalysisPhotoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "multi_analysis_id")
    val multiAnalysisId: Int,

    @ColumnInfo(name = "photo_analysis_id")
    val photoAnalysisId: Int
)