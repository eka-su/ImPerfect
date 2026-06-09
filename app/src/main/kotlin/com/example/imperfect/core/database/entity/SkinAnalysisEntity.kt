package com.example.imperfect.core.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.ColumnInfo
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "skin_analysis",
    foreignKeys = [
        ForeignKey(
            entity = PhotoAnalysisEntity::class,
            parentColumns = ["id"],
            childColumns = ["photo_analysis_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("photo_analysis_id")]
)
data class SkinAnalysisEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "photo_analysis_id")
    val photoAnalysisId: Int,

    val severity: String,

    @ColumnInfo(name = "predicted_count")
    val predictedCount: Double,

    @ColumnInfo(name = "severity_probabilities")
    val severityProbabilities: String,

    @ColumnInfo(name = "detected_count")
    val detectedCount: Int,

    @ColumnInfo(name = "final_severity")
    val finalSeverity: String,

    @ColumnInfo(name = "final_count")
    val finalCount: Int,

    @ColumnInfo(name = "confidence_score")
    val confidenceScore: Double,

    @ColumnInfo(name = "heatmap_path")
    val heatmapPath: String?,

    @ColumnInfo(name = "annotated_image_path")
    val annotatedImagePath: String?,

    @ColumnInfo(name = "created_at")
    val createdAt: String
)