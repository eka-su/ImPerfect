package com.example.imperfect.core.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.ColumnInfo
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "skin_issue",
    foreignKeys = [
        ForeignKey(
            entity = SkinAnalysisEntity::class,
            parentColumns = ["id"],
            childColumns = ["analysis_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("analysis_id")]
)
data class SkinIssueEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "analysis_id")
    val analysisId: Int,

    @ColumnInfo(name = "class_id")
    val classId: Int,

    val type: String?,

    val x: Double,
    val y: Double,
    val width: Double,
    val height: Double,

    val confidence: Double,

    val source: String
)