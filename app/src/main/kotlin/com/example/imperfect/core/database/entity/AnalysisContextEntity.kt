package com.example.imperfect.core.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "analysis_context",
    foreignKeys = [
        ForeignKey(
            entity = MultiImageAnalysisEntity::class,
            parentColumns = ["id"],
            childColumns = ["multi_analysis_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["multi_analysis_id"], unique = true)
    ]
)
data class AnalysisContextEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val multi_analysis_id: Int,

    val has_makeup: Int, // 0 / 1

    val note: String?
)