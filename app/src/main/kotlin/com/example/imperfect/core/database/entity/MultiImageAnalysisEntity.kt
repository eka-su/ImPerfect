package com.example.imperfect.core.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.ColumnInfo
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "multi_image_analysis",
    foreignKeys = [
        ForeignKey(
            entity = SkinDiaryDayEntity::class,
            parentColumns = ["id"],
            childColumns = ["diary_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("diary_id")]
)
data class MultiImageAnalysisEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "diary_id")
    val diaryId: Int,

    @ColumnInfo(name = "final_severity")
    val finalSeverity: String,

    @ColumnInfo(name = "average_acne_count")
    val averageAcneCount: Double,

    @ColumnInfo(name = "total_detections")
    val totalDetections: Int,

    @ColumnInfo(name = "average_confidence")
    val averageConfidence: Double,

    @ColumnInfo(name = "created_at")
    val createdAt: String
)