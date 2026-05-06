package com.example.imperfect.core.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.ColumnInfo
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "skin_issue_summary",
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
data class SkinIssueSummaryEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "analysis_id")
    val analysisId: Int,

    val type: String,

    val count: Int
)